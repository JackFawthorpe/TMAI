package com.backend.BLL;

import com.backend.UnitTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.SimpMessagingTemplate;


@SpringBootTest
class WebSocketServiceTest extends UnitTestFixture {
    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    private WebSocketService webSocketService;

    @BeforeEach
    void resetMocks() {
        Mockito.doNothing().when(messagingTemplate).convertAndSend(Mockito.eq("/topic/game"), (Object) Mockito.any());
    }

    @Test
    void pushGame_pushesGame() {
        webSocketService.pushGame();

        Mockito.verify(messagingTemplate, Mockito.times(1)).convertAndSend(Mockito.eq("/topic/game"), (Object) Mockito.any());
    }
}
