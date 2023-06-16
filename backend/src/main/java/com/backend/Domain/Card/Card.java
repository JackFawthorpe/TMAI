package com.backend.Domain.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity to represent a playable card in the game
 */
public class Card {

    private String title;

    private List<Tag> tags = new ArrayList<>();

    private int cost = -1;

    public Card() {}

    public Card(String title){
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {this.title = title;}

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
