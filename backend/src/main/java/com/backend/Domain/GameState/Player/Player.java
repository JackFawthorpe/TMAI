package com.backend.Domain.GameState.Player;

import com.backend.BLL.PlayerManagement.AIPlayerHandler;
import com.backend.BLL.PlayerManagement.HumanPlayerHandler;
import com.backend.BLL.PlayerManagement.PlayerHandler;
import com.backend.Domain.Card.Card;
import com.backend.Domain.GameState.Inventory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

/**
 * Represents a player within a game
 */
public class Player {

    private final Inventory inventory;
    private final String name;
    private final int id;
    @JsonIgnore
    private PlayerHandler handler;

    private PlayerDeck playerDeck;

    public Player(String name, int id) {
        this.name = name;
        this.inventory = new Inventory();
        this.handler = new AIPlayerHandler();
        this.id = id;
        this.playerDeck = new PlayerDeck();
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public PlayerHandler getHandler() {
        return handler;
    }

    public void setHandler(PlayerHandler handler) {
        this.handler = handler;
    }

    public boolean isHuman() {
        return handler instanceof HumanPlayerHandler;
    }

    public int getId() {
        return id;
    }

    public PlayerDeck getPlayerDeck() {
        return playerDeck;
    }

    public void setPlayerDeck(PlayerDeck playerDeck) {
        this.playerDeck = playerDeck;
    }

    /**
     * Gets the players hand
     *
     * @return the players hand
     */
    public ArrayList<Card> getHand() {
        return playerDeck.getHand();
    }
}
