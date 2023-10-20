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
    private boolean isHuman;
    @JsonIgnore
    private PlayerHandler handler;

    public Player(String name, int id) {
        this.name = name;
        this.inventory = new Inventory();
        this.handler = new AIPlayerHandler();
        this.isHuman = false;
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
        isHuman = handler instanceof HumanPlayerHandler;
        this.handler = handler;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public int getId() {
        return id;
    }
}
