package com.backend.BLL;

import com.backend.Domain.Action.Action;
import com.backend.Domain.GameState.Game;
import com.backend.Domain.GameState.Player.Player;
import org.springframework.stereotype.Service;

/**
 * Service to handle logic surrounding who can take turns when
 */
@Service
public class TurnService {

    private Player currentPlayer;
    private int currentIndex;

    /**
     * Checks if the player can make the action requested
     * Rules:
     * - It must be the players turn
     * - The game must have started
     * - The action provided is executable
     *
     * @param action The action to execute
     * @return
     */
    public boolean canDoAction(Action action) {

        Game game = Game.getGame();
        if (game == null) return false;
        Player[] players = game.getPlayers();

        if (!action.canExecute()) return false;

        if (currentPlayer == null) {
            currentIndex = 0;
            currentPlayer = players[currentIndex];
        }

        Player toPlay = action.getPlayer();
        return toPlay == currentPlayer;
    }

    /**
     * Takes the next turn in the game
     * Precondition:
     * - canDoAction has been called
     *
     * @param action The action to execute
     */
    public void takeTurn(Action action) {
        action.execute();
        nextPlayer();
    }

    /**
     * Gives the turn to the next player
     */
    public void nextPlayer() {
        Player[] players = Game.getGame().getPlayers();
        currentIndex = (currentIndex + 1) % players.length;
        currentPlayer = players[currentIndex];
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
