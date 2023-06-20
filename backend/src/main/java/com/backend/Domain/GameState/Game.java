package com.backend.Domain.GameState;

/**
 * Entity to represent the state of the game at any point
 */
public class Game {
    Player[] players;

    Board board = new Board();


    /**
     * Gets all the players that are involved in the game
     *
     * @return The players that are in the game
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Creates player entities to a max of 6 with the names specified by PlayerNames
     * Empty names will be set to "Player x" where x is the ith + 1 player
     * Precondition:
     * There is at least one player
     *
     * @param playerNames The names of the player in the order of play
     */
    public void setPlayers(String[] playerNames) {
        if (playerNames.length == 0) {
            throw new IllegalArgumentException("Cannot have an empty array");
        }
        int playerCount = Math.min(playerNames.length, 6);
        players = new Player[playerCount];
        for (int i = 0; i < playerCount; i++) {
            String name = playerNames[i].trim();
            players[i] = new Player(name.equals("") ? "Player " + (i + 1) : name.trim());
        }
    }

    /**
     * Gets the current state of the board for the game
     *
     * @return The board
     */
    public Board getBoard() {
        return board;
    }
}
