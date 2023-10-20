package com.backend.BLL.PlayerManagement;

/**
 * Represents the controller of a player within a game
 */
public interface PlayerHandler {

    /**
     * Requests for the next turn from this player
     */
    public void promptForTurn();
}
