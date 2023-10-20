package com.backend.Domain.GameState;

import com.backend.BLL.PlayerManagement.AIPlayerHandler;
import com.backend.BLL.PlayerManagement.PlayerHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a player within a game
 */
public class Player {

    Inventory inventory;
    String name;

    @JsonIgnore
    PlayerHandler handler;

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
        this.handler = new AIPlayerHandler();
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {return inventory;}

    public PlayerHandler getHandler() {
        return handler;
    }

    public void setHandler(PlayerHandler handler) {
        this.handler = handler;
    }
}
