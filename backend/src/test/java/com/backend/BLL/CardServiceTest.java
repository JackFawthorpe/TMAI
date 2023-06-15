package com.backend.BLL;

import com.backend.Domain.Card.Card;
import com.backend.Domain.Card.CardDAO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CardServiceTest {

    @Mock
    private CardDAO cardDAO;

    @InjectMocks
    private CardService cardService;

    @Test
    void getAllCards__AsksDAOForCards() {
        List<Card> cards = new ArrayList<>();
        Card card = new Card();
        card.setTitle("Test Card");
        cards.add(card);
        when(cardDAO.getAll()).thenReturn(cards);

        List<Card> result = cardService.getAllCards();

        assertEquals(cards, result);
        verify(cardDAO, Mockito.times(1)).getAll();
    }
}
