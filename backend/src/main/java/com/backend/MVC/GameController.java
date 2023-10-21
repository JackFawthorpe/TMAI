package com.backend.MVC;


import com.backend.BLL.CardService;
import com.backend.BLL.GameplayService;
import com.backend.BLL.TurnService;
import com.backend.BLL.WebSocketService;
import com.backend.Domain.Action.Action;
import com.backend.Domain.Action.PlayCardAction;
import com.backend.Domain.Card.Card;
import com.backend.Domain.GameState.Game;
import com.backend.Domain.GameState.Player.Player;
import com.backend.MVC.ViewModels.PlayCardViewModel;
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
    private TurnService turnService;

    @Autowired
    private CardService cardService;

    @Autowired
    private WebSocketService webSocketService;

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
     * @param seatIndex The index of player that is going to be controlled
     * @return 200 if successfully started to control otherwise 400
     */
    @PostMapping(value = "/game/play", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity playGame(@RequestBody int seatIndex) {
        logger.info("POST /game/play");
        try {
            gameService.addHumanPlayer(seatIndex);
            webSocketService.pushGame();
            return ResponseEntity.ok().body("");
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Given a valid pcvm the card will be played for the given player
     *
     * @param pcvm the body of the request (containing the seat index of the player and the card they are playing
     * @return 400 if the game hasn't started
     * 403 if the turn isn't currently allowed
     * 200 if the turn is successfully taken
     */
    @PostMapping(value = "/game/playcard", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity playCard(@RequestBody PlayCardViewModel pcvm) {
        logger.info("POST /game/playcard");

        Game game = Game.getGame();
        if (game == null) return ResponseEntity.badRequest().body("Game hasn't started");

        Player player = game.getPlayers()[pcvm.getSeatIndex()];
        Card card = cardService.getCardById(pcvm.getCardID());

        Action action = new PlayCardAction(player, card);

        if (!turnService.canTakeTurn(action)) {
            return ResponseEntity.status(403).body("This action cannot currently be taken");
        }

        turnService.takeTurn(action);
        return ResponseEntity.ok().body("");
    }
}
