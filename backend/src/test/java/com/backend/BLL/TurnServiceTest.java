package com.backend.BLL;

import com.backend.Domain.Action.Action;
import com.backend.Domain.GameState.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TurnServiceTest {

    @InjectMocks
    private TurnService turnService;

    private Action mockAction;

    @BeforeEach
    void resetMocks() {
        Game game = new Game();
        game.setPlayers(new String[]{"1", "2"});
        Game.setGame(game);

        mockAction = mock(Action.class);
        when(mockAction.getPlayer()).thenReturn(game.getPlayers()[0]);
        when(mockAction.canExecute()).thenReturn(true);
    }

    @Test
    void canDoAction_noPlayerSet_PlayerZeroIsTrue() {
        assertTrue(turnService.canDoAction(mockAction));
        verify(mockAction, times(1)).canExecute();
    }

    @Test
    void canDoAction_notPlayersTurn_False() {
        when(mockAction.getPlayer()).thenReturn(Game.getGame().getPlayers()[1]);
        assertFalse(turnService.canDoAction(mockAction));
    }

    @Test
    void canDoAction_noGame_False() {
        Game.setGame(null);
        assertFalse(turnService.canDoAction(mockAction));
    }

    @Test
    void canDoAction_CannotExecuteAction_False() {
        when(mockAction.canExecute()).thenReturn(false);
        assertFalse(turnService.canDoAction(mockAction));
    }

    @Test
    void takeTurn_Bluesky_works() {
        turnService = spy(turnService);
        doNothing().when(turnService).nextPlayer();
        turnService.takeTurn(mockAction);
        verify(mockAction, times(1)).execute();
        verify(turnService, times(1)).nextPlayer();
    }

    @Test
    void nextPlayer_0to1_works() {
        turnService.nextPlayer();
        assertEquals(Game.getGame().getPlayers()[1], turnService.getCurrentPlayer());
    }

    @Test
    void nextPlayer_1to0_works() {
        turnService.nextPlayer();
        assertEquals(Game.getGame().getPlayers()[1], turnService.getCurrentPlayer());
        turnService.nextPlayer();
        assertEquals(Game.getGame().getPlayers()[0], turnService.getCurrentPlayer());
    }
}
