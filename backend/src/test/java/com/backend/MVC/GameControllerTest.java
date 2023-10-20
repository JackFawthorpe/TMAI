package com.backend.MVC;

import com.backend.BLL.GameplayService;
import com.backend.Domain.GameState.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.backend.UnitTestFixture.toJson;
import static org.mockito.Mockito.when;
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


    @Test
    void createGame_NoPlayerNames_ReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/game")
                        .content("[]")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createGame_WithPlayerName_ReturnsGameWithPlayer() throws Exception {
        Game game = new Game();
        game.setPlayers(new String[]{"Test User"});
        when(gameService.createGame(Mockito.any())).thenReturn(game);

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
}
