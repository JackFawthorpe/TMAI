package com.backend.SpringConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


/**
 * Configuration for web sockets
 */
@Configuration
@EnableWebSocketMessageBroker
public class TMAIWebSocketConfig implements WebSocketMessageBrokerConfigurer {


    /**
     * Defines which endpoints are used to register a new stomp listener
     *
     * @param registry Its the registry that contains all the endpoints
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/game/socket")
                .setAllowedOrigins("*");
    }

    /**
     * Sets which endpoints are listened on and which are used to send messages
     * /app is the start of each endpoint that you can send on
     * /topic is subscribed to by the clients for listening to messages on
     *
     * @param config Its a config I guess idk
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app");
        config.enableSimpleBroker("/topic");
    }

}
