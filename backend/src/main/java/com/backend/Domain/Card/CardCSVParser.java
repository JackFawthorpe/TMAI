package com.backend.Domain.Card;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Retrieves cards from a local CSV
 */
@Component
public class CardCSVParser implements CardDAO {

    /**
     * Parses the full CSV and creates a list of cards
     * @return The list of cards
     */
    @Override
    public List<Card> getAll() {
        List<Card> cards = new ArrayList<>();
        Card card = new Card();
        card.setTitle("Test Card");
        cards.add(card);
        return cards;
    }
}
