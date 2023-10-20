package com.backend.MVC;


import com.backend.BLL.GameplayService;
import com.backend.Domain.GameState.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the endpoints related to simulating a game
 */
@RestController
public class GameController {

    Logger logger = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private GameplayService gameService;

    @Autowired
    private GameWebSocketController gameWebSocketController;

    /**
     * Endpoint for creating the singleton game
     *
     * @param playerNames The list of players to be in the game (Should not be larger than 6 as per service)
     * @return The Game as a JSON object
     */
    @PostMapping("/game")
    public ResponseEntity createGame(@RequestBody String[] playerNames) {
        if (playerNames.length == 0) {
            return ResponseEntity.badRequest().body("Must have at least one player");
        }
        return ResponseEntity.ok(gameService.createGame(playerNames));
    }

    /**
     * Returns a JSON object of the game state
     *
     * @return The game
     */
    @GetMapping("/game")
    public ResponseEntity getGame() {
        Game game = Game.getGame();
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }

    /**
     * Endpoint for letting users join the game
     *
     * @param playerId The index of player that is going to be controlled
     * @return 200 if successfully started to control otherwise 400
     */
    @PostMapping(value = "/game/play", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity playGame(@RequestBody int playerId) {
        logger.info("POST /game/play");
        try {
            gameService.addHumanPlayer(playerId);
            gameWebSocketController.pushGame();
            return ResponseEntity.ok().body("");
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
