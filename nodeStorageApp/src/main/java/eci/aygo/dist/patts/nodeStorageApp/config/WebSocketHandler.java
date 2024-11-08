package eci.aygo.dist.patts.nodeStorageApp.config;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

	private static Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

	private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		sessions.add(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
		sessions.remove(session);
		logger.info("WebSocket connection closed. Total connections: {}",
		sessions.size());
	}

	public void notifyAllClients(Object message) {
		try {
			String jsonMessage = objectMapper.writeValueAsString(message);
			TextMessage textMessage = new TextMessage(jsonMessage);

			sessions.forEach(session -> {
				try {
					if (session.isOpen()) {
						session.sendMessage(textMessage);
					}
				} catch (IOException e) {
					logger.error("Error sending message to WebSocket client", e);
				}
			});
		} catch (IOException e) {
			logger.error("Error converting message to JSON", e);
		}
	}
}