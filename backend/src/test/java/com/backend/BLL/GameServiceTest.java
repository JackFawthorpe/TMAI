package com.backend.BLL;

import com.backend.Domain.GameState.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class GameServiceTest {

    GameService service;

    @BeforeEach
    void reset() {
        service = new GameService();
    }

    @Test
    void createGame_WithPlayerName_CreatesGameWithPlayer() {
        Game result = service.createGame(new String[]{"Bob", "Cob"});
        assertEquals(2, result.getPlayers().length);
        assertEquals("Bob", result.getPlayers()[0].getName());
        assertEquals("Cob", result.getPlayers()[1].getName());
    }

    @Test
    void createGame_WithMoreThan6Players_CreatesGameWithPlayer() {
        Game result = service.createGame(new String[]{"Bob", "Cob", "Cob", "Cob", "Cob", "Cob", "Cob", "Cob"});
        assertEquals(6, result.getPlayers().length);
    }

    @Test
    void getGame_notCreated_returnsNull() {
        Game result = service.getGame();
        assertNull(result);
    }
    @Test
    void createGame_ValidGame_Persists() {
        Game created = service.createGame(new String[]{"Test"});
        Game gotten = service.getGame();
        assertSame(created, gotten);
    }

}
