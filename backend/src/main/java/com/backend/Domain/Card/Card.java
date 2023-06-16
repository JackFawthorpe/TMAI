package com.backend.Domain.Card;

/**
 * Entity to represent a playable card in the game
 */
public class Card {

    private String title;

    public Card() {}

    public Card(String title){
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {this.title = title;}

}
