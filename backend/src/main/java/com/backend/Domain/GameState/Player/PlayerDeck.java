package com.backend.Domain.GameState.Player;

import com.backend.Domain.Card.Card;

import java.util.ArrayList;

/**
 * Representation of the cards that a player owns within a given game
 */
public class PlayerDeck {

    private ArrayList<Card> hand;
    private ArrayList<Card> played;

    public PlayerDeck() {
        hand = new ArrayList<>();
        played = new ArrayList<>();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public ArrayList<Card> getPlayed() {
        return played;
    }

    public void setPlayed(ArrayList<Card> played) {
        this.played = played;
    }
}
