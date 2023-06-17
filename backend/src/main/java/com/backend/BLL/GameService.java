package com.backend.BLL;

import com.backend.Domain.GameState.Game;
import org.springframework.stereotype.Service;

/**
 * Runs a singleton instance of the game for the website
 */
@Service
public class GameService {

    private Game currentGame;

    /**
     * Instantiates the game for the website
     * Preconditions : There is at least one player in the list
     * Post conditions : The first six players in the list will be added to the game
     *
     * @param playerNames The players of the game in order or play
     * @return The instance of the game
     */
    public Game createGame(String[] playerNames) {
        Game game = new Game();
        game.setPlayers(playerNames);
        this.currentGame = game;
        return game;
    }

    /**
     * Gets the game that is currently in play
     *
     * @return The game
     */
    public Game getGame() {
        return this.currentGame;
    }
}
