package com.backend.BLL;

import com.backend.BLL.PlayerManagement.HumanPlayerHandler;
import com.backend.Domain.GameState.Game;
import com.backend.Domain.GameState.Player;
import org.springframework.stereotype.Service;

/**
 * Runs a singleton instance of the game for the website
 */
@Service
public class GameplayService {

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
        Game.setGame(game);
        return game;
    }

    public void addHumanPlayer(int playerId) throws IllegalArgumentException {
        Game game = Game.getGame();
        Player[] players = game.getPlayers();
        if (players[playerId].isHuman()) {
            throw new IllegalArgumentException("Player is already controlled by a player");
        }
        players[playerId].setHandler(new HumanPlayerHandler());
    }
}
