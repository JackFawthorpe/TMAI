package com.backend.BLL;

import com.backend.Domain.Card.Card;
import com.backend.Domain.Card.CardDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CardServiceTest {

    List<Card> cards;
    @Mock
    private CardDAO cardDAO;
    @InjectMocks
    private CardService cardService;

    @BeforeEach
    void resetMocks() {
        cards = new ArrayList<>();
        Card card = new Card();
        card.setTitle("Test Card");
        cards.add(card);
        when(cardDAO.getAll()).thenReturn(cards);
    }

    @Test
    void getAllCards__AsksDAOForCards() {
        List<Card> result = cardService.getAllCards();

        assertEquals(cards, result);
        verify(cardDAO, Mockito.times(1)).getAll();
    }

    @Test
    void drawFromAllCards_Draw1_Adds1Card() {
        List<Card> deck = new ArrayList<>();
        cardService.drawFromAllCards(1, deck);
        assertEquals(1, deck.size());
        assertEquals(cards.get(0), deck.get(0));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void drawFromAllCards_LessThanOr0_NoAdditions(int toAdd) {
        List<Card> deck = new ArrayList<>();
        cardService.drawFromAllCards(toAdd, deck);
        assertEquals(0, deck.size());
    }

    @Test
    void drawFromAllCards_MoreThanDAOSize_StacksDeck() {
        List<Card> deck = new ArrayList<>();
        cardService.drawFromAllCards(3, deck);
        assertEquals(3, deck.size());
    }
}
