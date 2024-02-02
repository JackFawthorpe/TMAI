package com.backend.Domain.Action;

import com.backend.Domain.Card.Card;
import com.backend.Domain.GameState.Player.Player;

/**
 * Action to represent a player playing a card
 */
public class PlayCardAction extends Action {

    Card card;

    public PlayCardAction(Player player, Card card) {
        this.card = card;
        this.player = player;
    }

    public Card getCard() {
        return card;
    }


    /**
     * Checks if the card can currently be played
     * Rules:
     * - The card is in the players hand
     *
     * @return True if the card can be played by this player
     */
    public boolean canExecute() {
        return player.getHand().contains(card);
    }

    /**
     * Executes the action (playing a card)
     * This means:
     * - The card is taken from their hand
     * - The card is placed in the played section of their hand
     */
    @Override
    public void execute() {
        player.playCard(card);
    }
}
