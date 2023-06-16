package com.backend.Domain.Card;

import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Retrieves cards from a local CSV
 */
@Component
public class CardCSVParser implements CardDAO {

    Logger logger = LoggerFactory.getLogger(CardCSVParser.class);

    /**
     * Parses the full CSV and creates a list of cards
     * @return The list of cards
     */

    @Override
    public List<Card> getAll() {
        try {
            List<String[]> cardStringArrays = readAllLines();
            return createCardList(cardStringArrays);
        } catch (Exception e) {
            logger.error("Cards failed to load from CSV: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Reads all lines of the card CSV
     * @return A list of string array's one for each line of the CSV
     * @throws IOException When the CSV cannot be read
     * @throws URISyntaxException Invalid URI (Not possible)
     */
    private List<String[]> readAllLines() throws IOException, URISyntaxException {
        Path path = Paths.get(
                ClassLoader.getSystemResource("card.csv").toURI());
        try (Reader reader = Files.newBufferedReader(path)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }

    /**
     * Takes a CSV of the following format and converts it into a list of cards
     * | Title |
     * @param stringArrays The list of string arrays to parse into Cards (With a header row)
     * @return The list of cards that are created
     */
    private List<Card> createCardList(List<String[]> stringArrays){
        List<Card> cards = new ArrayList<>();
        for (int i = 1; i < stringArrays.size(); i++){
            cards.add(new Card(stringArrays.get(i)[0]));
        }
        return cards;
    }
}
