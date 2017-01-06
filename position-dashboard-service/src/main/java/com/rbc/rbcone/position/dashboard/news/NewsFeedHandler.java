package com.rbc.rbcone.position.dashboard.news;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class NewsFeedHandler extends TextWebSocketHandler {

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("message: " + message.getPayload());
		TextMessage responseMessage = new TextMessage("bar", false);
		session.sendMessage(responseMessage);
	}
}