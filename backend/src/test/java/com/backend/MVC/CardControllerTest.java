package com.backend.MVC;


import com.backend.BLL.CardService;
import com.backend.Domain.Card.Card;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static com.backend.UnitTestFixture.toJson;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest(controllers = CardController.class)
@AutoConfigureMockMvc()
class CardControllerTest {

    @MockBean
    private CardService cardService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllCards_NoCards_Returns200() {
        try {
            List<Card> cards = new ArrayList<>();
            when(cardService.getAllCards()).thenReturn(cards);
            MvcResult result = mockMvc.perform(get("/cards"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json(toJson(cards)))
                    .andReturn();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getAllCards_Cards_Returns200() {
        try {
            List<Card> cards = new ArrayList<>();
            Card card1 = new Card();
            Card card2 = new Card();
            Card card3 = new Card();
            card1.setTitle("First");
            card2.setTitle("Second");
            card3.setTitle("Third");
            cards.add(card1);
            cards.add(card2);
            cards.add(card3);
            when(cardService.getAllCards()).thenReturn(cards);
            MvcResult result = mockMvc.perform(get("/cards"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json(toJson(cards)))
                    .andReturn();
        } catch (Exception e) {
            fail();
        }
    }
}
