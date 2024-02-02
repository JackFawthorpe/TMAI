package com.backend.Domain.Action;

import com.backend.Domain.GameState.Player.Player;

/**
 * Class to represent the action a player is taking
 */
public abstract class Action {

    protected Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public abstract boolean canExecute();

    public abstract void execute();
}
