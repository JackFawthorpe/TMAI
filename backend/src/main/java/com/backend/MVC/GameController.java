package com.backend.MVC;


import com.backend.BLL.GameService;
import com.backend.Domain.GameState.Game;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    GameService gameService;

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
        Game game = gameService.getGame();
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }
}
