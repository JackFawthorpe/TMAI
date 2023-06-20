package com.backend.Domain.GameState;

/**
 * Represents a player within a game
 */
public class Player {

    Inventory inventory;
    String name;

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {return inventory;}
}
