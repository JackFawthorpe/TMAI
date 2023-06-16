package com.backend.Domain.Card;

import com.backend.Domain.Utility.CSVReader;
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

    CSVReader csvReaderMock;
    String[] headers = {"Title", "Cost"};
    private CardDAO csvParser;

    @BeforeEach
    void resetMocks() {
        this.csvReaderMock = Mockito.mock(CSVReader.class);
        this.csvParser = new CardCSVParser(csvReaderMock);
    }

    @Test
    void getAll_ValidCSV_ReturnsCard() throws IOException, URISyntaxException {
        String[] row = {"Test Title", "5"};
        List<String[]> rows = new ArrayList<>();
        rows.add(headers);
        rows.add(row);
        when(csvReaderMock.readAllLines("card.csv")).thenReturn(rows);

        List<Card> result = csvParser.getAll();

        verify(csvReaderMock, Mockito.times(1)).readAllLines("card.csv");
        assertEquals(1, result.size());
        assertEquals("Test Title", result.get(0).getTitle());
        assertEquals(5, result.get(0).getCost());
    }

    @Test
    void getAll_FailedToReadCSV_ReturnsEmptyList() throws IOException, URISyntaxException {
        String[] row = {"Test Title", "5"};
        List<String[]> rows = new ArrayList<>();
        rows.add(headers);
        rows.add(row);
        when(csvReaderMock.readAllLines("card.csv")).thenThrow(new IOException("Failed"));

        List<Card> result = csvParser.getAll();

        verify(csvReaderMock, Mockito.times(1)).readAllLines("card.csv");
        assertEquals(0, result.size());
    }
}
