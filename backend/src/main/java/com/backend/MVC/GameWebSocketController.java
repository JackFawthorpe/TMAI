package com.backend.MVC;

import com.backend.BLL.GameplayService;
import com.backend.Domain.GameState.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Controller for handling web socket messages for games
 */
@Controller
public class GameWebSocketController {

    Logger logger = LoggerFactory.getLogger(GameWebSocketController.class);

    @Autowired
    private GameplayService gameService;

    /**
     * Endpoint to push and receive turns from
     * @param message Most recent message
     * @return The current state of the game
     */
    @MessageMapping("/game/turn")
    @SendTo("/topic/game/turn")
    public Game takeTurn(String message) {
        logger.info("MESSAGE /game/turn");

        return Game.getGame();
    }
}
