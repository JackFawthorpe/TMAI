package com.backend.Domain;

import com.backend.Domain.GameState.Game;
import com.backend.Domain.GameState.Player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameTest {

    @Test
    void setPlayers_validPlayers_savesPlayers() {
        Game game = new Game();
        game.setPlayers(new String[]{"Bob", "Cob"});
        Player[] result = game.getPlayers();
        assertEquals(2, result.length);
        assertEquals("Bob", result[0].getName());
        assertEquals("Cob", result[1].getName());
    }

    @Test
    void setPlayers_moreThanSix_CutsToSix() {
        Game game = new Game();
        game.setPlayers(new String[]{"1", "2", "3", "4", "5", "6", "7"});
        Player[] result = game.getPlayers();
        assertEquals(6, result.length);
    }

    @Test
    void setPlayers_NoPlayers_IllegalArgThrown() {
        Game game = new Game();
        assertThrows(IllegalArgumentException.class, () -> {
            game.setPlayers(new String[]{});
        });
    }
}
