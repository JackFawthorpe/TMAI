package com.backend.MVC;

import com.backend.BLL.CardService;
import com.backend.BLL.GameplayService;
import com.backend.BLL.TurnService;
import com.backend.BLL.WebSocketService;
import com.backend.Domain.GameState.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.backend.UnitTestFixture.toJson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest(controllers = GameController.class)
@AutoConfigureMockMvc()
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameplayService gameService;

    @MockBean
    private WebSocketService webSocketService;

    @MockBean
    private TurnService turnService;

    @MockBean
    private CardService cardService;

    private Game game;

    @BeforeEach
    void resetMocks() {
        game = new Game();
        game.setPlayers(new String[]{"Test User"});

        doNothing().when(gameService).addHumanPlayer(anyInt());
        when(gameService.createGame(any())).thenReturn(game);

        doNothing().when(webSocketService).pushGame();
    }


    @Test
    void createGame_NoPlayerNames_ReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/game")
                        .content("[]")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createGame_WithPlayerName_ReturnsGameWithPlayer() throws Exception {
        mockMvc.perform(post("/game")
                        .content("[\"Test User\"]")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(game)));
    }

    @Test
    void getGame_WithGame_ReturnsGame() throws Exception {
        Game game = new Game();
        game.setPlayers(new String[]{"Test User"});
        Game.setGame(game);
        mockMvc.perform(get("/game"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(game)));
    }

    @Test
    void getGame_WithNoGame_ReturnsNotFound() throws Exception {
        Game.setGame(null);
        mockMvc.perform(get("/game"))
                .andExpect(status().isNotFound());
    }

    @Test
    void playGame_Bluesky_200() throws Exception {
        mockMvc.perform(post("/game/play")
                        .content("0")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void playGame_GameServiceFails_400() throws Exception {
        doThrow(new IllegalArgumentException("Failed")).when(gameService).addHumanPlayer(anyInt());
        mockMvc.perform(post("/game/play")
                        .content("0")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    
}
