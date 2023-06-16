package com.backend.Domain.Card;

import com.backend.Domain.Utility.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Retrieves cards from a local CSV
 */
@Component
public class CardCSVParser implements CardDAO {

    private final CSVReader csvReader;

    public CardCSVParser() {
        this.csvReader = new CSVReader();
    }

    public CardCSVParser(CSVReader csvReader) {
        this.csvReader = csvReader;
    }

    Logger logger = LoggerFactory.getLogger(CardCSVParser.class);

    /**
     * Parses the full CSV and creates a list of cards
     * @return The list of cards
     */
    @Override
    public List<Card> getAll() {
        try {
            List<String[]> cardStringArrays = csvReader.readAllLines("card.csv");
            return createCardList(cardStringArrays);
        } catch (Exception e) {
            logger.error("Cards failed to load from CSV: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Takes a CSV of the following format and converts it into a list of cards
     * | Title | Cost |
     * @param stringArrays The list of string arrays to parse into Cards (With a header row)
     * @return The list of cards that are created
     */
    private List<Card> createCardList(List<String[]> stringArrays) {
        List<Card> cards = new ArrayList<>();
        for (int i = 1; i < stringArrays.size(); i++){
            cards.add(buildCard(stringArrays.get(i)));
        }
        return cards;
    }

    /**
     * Takes card details from CSV matching
     * | Title | Cost |
     * and turns it into a card entity using {@link CardBuilder}
     * @param cardDetails The row from the CSV
     * @return The card built
     */
    private Card buildCard(String[] cardDetails) {
        CardBuilder builder = new CardBuilder(cardDetails[0]);
        builder.withCost(cardDetails[1]);
        return builder.build();
    }
}
