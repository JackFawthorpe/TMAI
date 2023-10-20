package com.backend.Domain.GameState;

/**
 * Singleton Pattern for data about the current game that is being played
 */
public class Game {

    // Singleton Pattern
    private static Game currentGame = null;
    private final Board board = new Board();
    private final GlobalParameters globalParameters = new GlobalParameters();
    private Player[] players;

    public static Game getGame() {
        return currentGame;
    }

    public static void setGame(Game game) {
        currentGame = game;
    }

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
            players[i] = new Player(name.equals("") ? "Player " + (i + 1) : name.trim(), i);
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

    /**
     * Gets the global parameters of the game (water, o2 and temperature)
     *
     * @return The global parameters of the game
     */
    public GlobalParameters getGlobalParameters() {
        return globalParameters;
    }
}
