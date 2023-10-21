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

    /**
     * Makes the seat (seatIndex) controlled by a human instead of an AI
     * Rules:
     * - Seat index is out of players[] bounds
     * - Game hasn't started
     * - Player at seat index is already a human
     *
     * @param seatIndex The seat ID
     * @throws IllegalArgumentException Thrown if rules broken
     */
    public void addHumanPlayer(int seatIndex) throws IllegalArgumentException {
        Game game = Game.getGame();
        if (game == null) {
            throw new IllegalArgumentException("Game hasn't been started");
        }
        Player[] players = game.getPlayers();

        if (seatIndex < 0 || seatIndex >= players.length) {
            throw new IllegalArgumentException("Seat index out of bounds");
        }

        if (players[seatIndex].isHuman()) {
            throw new IllegalArgumentException("Player is already controlled by a human");
        }
        players[seatIndex].setHandler(new HumanPlayerHandler());
    }
}
