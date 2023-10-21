package com.backend.Domain.GameState;

import com.backend.BLL.PlayerManagement.AIPlayerHandler;
import com.backend.BLL.PlayerManagement.HumanPlayerHandler;
import com.backend.BLL.PlayerManagement.PlayerHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a player within a game
 */
public class Player {

    private final Inventory inventory;
    private final String name;
    private final int id;
    @JsonIgnore
    private PlayerHandler handler;

    public Player(String name, int id) {
        this.name = name;
        this.inventory = new Inventory();
        this.handler = new AIPlayerHandler();
        this.id = id;
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
}
