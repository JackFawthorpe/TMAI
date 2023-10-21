package com.backend.BLL;

import com.backend.Domain.GameState.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Controller for handling web socket messages for games
 */
@Service
public class WebSocketService {

    Logger logger = LoggerFactory.getLogger(WebSocketService.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Allows normal endpoints to push updates about the game
     */
    public void pushGame() {
        logger.info("Pushing updated game to /topic/game");
        messagingTemplate.convertAndSend("/topic/game", Game.getGame());
    }
}
