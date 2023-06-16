package com.backend.Domain.Card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builder Pattern for creating a card
 */
public class CardBuilder {

    Logger logger = LoggerFactory.getLogger(CardBuilder.class);

    private Card card;

    /**
     * Instantiates the builder thus the card
     * @param title The title of the card
     */
    public CardBuilder(String title) {
        this.card = new Card(title);
    }

    /**
     * Parses the string into an integer for the cost of the card
     * If the String doesn't represent a cost it will fall back to -1
     * @param cost The cost of the card
     */
    public void withCost(String cost) {
        try {
            card.setCost(Integer.valueOf(cost));
        } catch (NumberFormatException e) {
            logger.error("Failed to parse cost of card {}: {}", card.getTitle(), e.getMessage());
            card.setCost(-1);
        }
    }

    /**
     * Finalizes the card
     * @return The card that has been built
     */
    public Card build() {
        return this.card;
    }
}
