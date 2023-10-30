package com.backend.Domain.Card;

import com.backend.BLL.Parsing.CSVReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CardCSVParserTest {

    String[] HEADERS = {"Title", "Cost", "Building", "Space", "Science", "Plant", "Microbe", "Animal", "Power", "Jovian", "Earth", "City", "Event"};
    String[] TEST_CARD = {"Test Title", "32", "1", "", "", "1", "1", "", "", "1", "1", "", "1"};
    CSVReader csvReaderMock;
    private CardDAO csvParser;

    @BeforeEach
    void resetMocks() {
        this.csvReaderMock = Mockito.mock(CSVReader.class);
        this.csvParser = new CardCSVParser(csvReaderMock);
    }

    @Test
    void getAll_ValidCSV_ReturnsCard() throws IOException, URISyntaxException {
        List<String[]> rows = new ArrayList<>();
        rows.add(HEADERS);
        rows.add(TEST_CARD);
        when(csvReaderMock.readAllLines("card.csv")).thenReturn(rows);

        List<Card> result = csvParser.getAll();

        verify(csvReaderMock, Mockito.times(1)).readAllLines("card.csv");
        assertEquals(1, result.size());
        assertEquals("Test Title", result.get(0).getTitle());
        assertEquals(32, result.get(0).getCost());
    }

    @Test
    void getAll_FailedToReadCSV_ReturnsEmptyList() throws IOException, URISyntaxException {
        List<String[]> rows = new ArrayList<>();
        rows.add(HEADERS);
        rows.add(TEST_CARD);
        when(csvReaderMock.readAllLines("card.csv")).thenThrow(new IOException("Failed"));

        List<Card> result = csvParser.getAll();

        verify(csvReaderMock, Mockito.times(1)).readAllLines("card.csv");
        assertEquals(0, result.size());
    }

    @Test
    void getALL_TestFirstLastMiddleTag_CorrectEnum() throws IOException, URISyntaxException {
        String[] test_row = {"Test Title", "32", "1", "", "", "", "", "1", "", "", "", "", "1"};
        List<String[]> rows = new ArrayList<>();
        rows.add(HEADERS);
        rows.add(test_row);
        when(csvReaderMock.readAllLines("card.csv")).thenReturn(rows);
        List<Card> result = csvParser.getAll();
        assertEquals(Tag.BUILDING, result.get(0).getTags().get(0));
        assertEquals(Tag.ANIMAL, result.get(0).getTags().get(1));
        assertEquals(Tag.EVENT, result.get(0).getTags().get(2));
    }
}
