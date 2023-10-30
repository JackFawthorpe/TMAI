package com.backend.Domain.Card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Builder Pattern for creating a card
 */
public class CardBuilder {

    private final Card card;
    Logger logger = LoggerFactory.getLogger(CardBuilder.class);

    /**
     * Instantiates the builder thus the card
     *
     * @param title The title of the card
     */
    public CardBuilder(String title, int index) {
        this.card = new Card(title == null || title.equals("") ? "No Title Provided" : title, index);
    }

    /**
     * Parses the string into an integer for the cost of the card
     * If the String doesn't represent a cost it will fall back to -1
     *
     * @param cost The cost of the card
     * @return the builder
     */
    public CardBuilder withCost(String cost) {
        try {
            card.setCost(Integer.parseInt(cost));
        } catch (NumberFormatException e) {
            logger.error("Failed to parse cost of card {}: {}", card.getTitle(), e.getMessage());
            card.setCost(-1);
        }
        return this;
    }

    /**
     * Takes a tag and adds it to list of tags for a card
     * If the tag is null then nothing will be added
     *
     * @param tag The tag to be added to a card
     * @return the builder
     */
    public CardBuilder withTag(Tag tag) {
        if (tag != null) {
            List<Tag> tags = card.getTags();
            tags.add(tag);
            card.setTags(tags);
        }
        return this;
    }

    /**
     * Finalizes the card
     *
     * @return The card that has been built
     */
    public Card build() {
        return card;
    }
}
