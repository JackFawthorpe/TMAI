package com.backend.Domain.Card;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Proxies the CardCSV so that instead of pulling from
 * CSV every time we can do it once and store in memory
 */
@Primary
@Component
public class CardCSVProxy implements CardDAO {


    CardDAO csvParser;

    private List<Card> cards = null;

    /**
     * Dependency Injection constructor
     */
    public CardCSVProxy(CardCSVParser parser) {
        this.csvParser = parser;
    }

    /**
     * Spring constructor for use with @Autowired
     */
    public CardCSVProxy() {
        this.csvParser = new CardCSVParser();
    }

    /**
     * If the cards have been previously loaded it returns the one in
     * memory otherwise it will retrieve the cards from memory
     *
     * @return The cards being used by application
     */
    @Override
    public List<Card> getAll() {
        if (cards != null) {
            return cards;
        }

        cards = csvParser.getAll();
        return cards;
    }

    @Override
    public Card getById(int id) {
        if (cards == null) {
            cards = csvParser.getAll();
        }

        return cards.get(id);
    }
}
