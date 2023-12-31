package com.backend.Domain.Card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CardCSVProxyTest {

    CardCSVProxy cardProxy;

    CardCSVParser parserMock;

    @BeforeEach
    void setProxy() {
        parserMock = Mockito.mock(CardCSVParser.class);
        cardProxy = new CardCSVProxy(parserMock);
    }

    @Test
    void getAll_SecondFetch_CardProxyCachesResults() {
        List<Card> cards = new ArrayList<>();
        Card card = new Card();
        card.setTitle("Test Card");
        cards.add(card);
        when(parserMock.getAll()).thenReturn(cards);

        List<Card> result = cardProxy.getAll();
        assertEquals(cards, result);
        cardProxy.getAll();
        assertEquals(cards, result);

        verify(parserMock, Mockito.times(1)).getAll();
    }
}
