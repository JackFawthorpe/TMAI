package com.backend.BLL;

import com.backend.Domain.Card.Card;
import com.backend.Domain.Card.CardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Class for handling the business logic mapping the endpoints
 * to the Domain
 */
@Service
public class CardService {

    @Autowired
    CardDAO cardDAO;

    /**
     * Gets all the cards that are known to the system
     *
     * @return The cards that are being used by the application
     */
    public List<Card> getAllCards() {
        return cardDAO.getAll();
    }

    /**
     * Draws cards to the list provided
     * This is done through shuffling every stored card and taking the first cardCount in the list
     * if the draw count is larger than the full deck it will continually append the list till its enough
     *
     * @param cardCount The amount of cards to draw
     * @param toAdd     The list to add cards to
     */
    public void drawFromAllCards(int cardCount, List<Card> toAdd) throws IllegalArgumentException {
        List<Card> cards = cardDAO.getAll();
        while (cards.size() < cardCount) {
            cards.addAll(cardDAO.getAll());
        }
        Collections.shuffle(cards);
        for (int i = 0; i < cardCount; i++) {
            toAdd.add(cards.get(i));
        }
    }
}
