package com.backend.Domain.Action;

import com.backend.Domain.Card.Card;
import com.backend.Domain.GameState.Player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class PlayCardActionTest {

    Card mockCard;
    Player mockPlayer;

    @BeforeEach
    void reset() {
        mockCard = mock(Card.class);
        mockPlayer = mock(Player.class);
        when(mockPlayer.getHand()).thenReturn(List.of(mockCard));
    }

    @Test
    void canExecute_playerHasCardInHand_True() {
        Action action = new PlayCardAction(mockPlayer, mockCard);
        Assertions.assertTrue(action.canExecute());
    }

    @Test
    void canExecute_CardNotInHand_False() {
        when(mockPlayer.getHand()).thenReturn(List.of());

        Action action = new PlayCardAction(mockPlayer, mockCard);
        Assertions.assertFalse(action.canExecute());
    }

    @Test
    void execute_callsPlayerToExecute() {
        Action action = new PlayCardAction(mockPlayer, mockCard);
        action.execute();
        verify(mockPlayer, times(1)).playCard(mockCard);
    }
}
