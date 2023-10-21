package com.backend.Domain.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity to represent a playable card in the game
 */
public class Card {

    private String title;

    private int index;
    private List<Tag> tags = new ArrayList<>();

    private int cost = -1;

    public Card() {
    }

    public Card(String title, int index) {
        this.title = title;
        this.index = index;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int hashCode() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Card card)) return false;
        return this.index == card.index;
    }
}
