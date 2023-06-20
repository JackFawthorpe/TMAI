package com.backend.Domain.GameState;

/**
 * Inventory of a player during a game
 */
public class Inventory {
    int[] production = new int[] {0,0,0,0,0,0};
    int[] count = new int[] {0,0,0,0,0,0};

    public int[] getProduction() {
        return production;
    }

    public void setProduction(int[] production) {
        this.production = production;
    }

    public int[] getCount() {
        return count;
    }

    public void setCount(int[] count) {
        this.count = count;
    }
}
