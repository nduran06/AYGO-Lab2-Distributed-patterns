package eci.aygo.dist.patts.nodeStorageApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import eci.aygo.dist.patts.nodeStorageApp.config.WebSocketHandler;


public class WebSocketConfig implements WebSocketConfigurer {

	@Autowired
    private WebSocketHandler webSocketHandler;
	
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/ws")
               .setAllowedOrigins("*");
    }
}
