package com.backend.Domain.Card;

import com.backend.BLL.Parsing.CSVReader;
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

    private final int TITLE_COL = 0;
    private final int COST_COL = 1;
    private final int TAG_START_COL = 2;
    private final int TAG_END_COL = 12;
    private final CSVReader csvReader;
    Logger logger = LoggerFactory.getLogger(CardCSVParser.class);

    public CardCSVParser() {
        this.csvReader = new CSVReader();
    }

    public CardCSVParser(CSVReader csvReader) {
        this.csvReader = csvReader;
    }

    /**
     * Parses the full CSV and creates a list of cards
     *
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
     * Generates the list of all cards and return the card that wil have the same id.
     * Note this shouldn't be called as the proxy should make this function untouched
     *
     * @param id The id of the card to fetch
     * @return The card that matches the id or null if the cards ID doesnt exist
     */
    @Override
    public Card getById(int id) {
        try {
            List<String[]> cardStringArrays = csvReader.readAllLines("card.csv");
            return createCardList(cardStringArrays).get(id);
        } catch (Exception e) {
            logger.error("Cards failed to load from CSV: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Takes a CSV of the following format and converts it into a list of cards
     * | Title | Cost | Building | Space | Science | Plant | Microbe | Animal | Power | Jovian | Earth | City | Earth |
     *
     * @param stringArrays The list of string arrays to parse into Cards (With a header row)
     * @return The list of cards that are created
     */
    private List<Card> createCardList(List<String[]> stringArrays) {
        List<Card> cards = new ArrayList<>();
        for (int i = 1; i < stringArrays.size(); i++) {
            Card card = buildCard(stringArrays.get(i), i - 1);
            if (card != null) {
                cards.add(card);
            }
        }
        return cards;
    }

    /**
     * Takes card details from CSV matching
     * | Title | Cost | Building | Space | Science | Plant | Microbe | Animal | Power | Jovian | Earth | City | Earth |
     * and turns it into a card entity using {@link CardBuilder}
     * if card fails to build then null will be returned
     *
     * @param cardDetails The row from the CSV
     * @param index       the index of the card within the csv (0-based)
     * @return The card built
     */
    private Card buildCard(String[] cardDetails, int index) {
        try {
            CardBuilder builder = new CardBuilder(cardDetails[TITLE_COL], index);
            builder.withCost(cardDetails[COST_COL]);
            for (int i = TAG_START_COL; i <= TAG_END_COL; i++) {
                if (cardDetails[i].equals("1")) {
                    builder.withTag(Tag.values()[i - TAG_START_COL]);
                }
            }
            return builder.build();
        } catch (Exception e) {
            logger.error("error creating card: {}", cardDetails[TITLE_COL]);
            return null;
        }
    }
}
