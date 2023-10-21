package com.backend.BLL;

import com.backend.BLL.PlayerManagement.HumanPlayerHandler;
import com.backend.Domain.GameState.Game;
import com.backend.Domain.GameState.Player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameplayServiceTest {

    @InjectMocks
    GameplayService gameplayService;

    @BeforeEach
    void resetMocks() {
        Game game = new Game();
        game.setPlayers(new String[]{"1", "2"});
        Game.setGame(game);
    }

    @Test
    void createGame_WithPlayerName_CreatesGameWithPlayer() {
        Game result = gameplayService.createGame(new String[]{"Bob", "Cob"});
        assertEquals(2, result.getPlayers().length);
        assertEquals("Bob", result.getPlayers()[0].getName());
        assertEquals("Cob", result.getPlayers()[1].getName());
    }

    @Test
    void createGame_WithMoreThan6Players_CreatesGameWithPlayer() {
        Game result = gameplayService.createGame(new String[]{"Bob", "Cob", "Cob", "Cob", "Cob", "Cob", "Cob", "Cob"});
        assertEquals(6, result.getPlayers().length);
    }

    @Test
    void createGame_ValidGame_Persists() {
        Game created = gameplayService.createGame(new String[]{"Test"});
        Game gotten = Game.getGame();
        assertSame(created, gotten);
    }

    @Test
    void addHumanPlayer_Bluesky_NoError() {
        try {
            gameplayService.addHumanPlayer(0);
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void addHumanPlayer_GameHasntStarted_IllegalArgumentException() {
        Game.setGame(null);
        Throwable e = assertThrows(IllegalArgumentException.class, () -> gameplayService.addHumanPlayer(0));
        assertEquals("Game hasn't been started", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 2, 3})
    void addHumanPlayer_PlayerDoesntExist_IllegalArgumentException(int seatIndex) {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> gameplayService.addHumanPlayer(seatIndex));
        assertEquals("Seat index out of bounds", e.getMessage());
    }

    @Test
    void addHumanPlayer_PlayerIsAlreadyHuman_IllegalArgumentException() {
        Player[] players = Game.getGame().getPlayers();
        players[1].setHandler(new HumanPlayerHandler());
        Throwable e = assertThrows(IllegalArgumentException.class, () -> gameplayService.addHumanPlayer(1));
        assertEquals("Player is already controlled by a human", e.getMessage());
    }
}
