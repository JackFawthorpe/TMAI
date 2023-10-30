package com.backend.Domain.Card;

import java.util.List;

/**
 * Data access object for cards
 */
public interface CardDAO {

    /**
     * Retrieves the cards to be used by the application
     *
     * @return The cards to be used
     */
    List<Card> getAll();

    /**
     * Retrieves the card that matches the ID given
     */
    Card getById(int id);
}
