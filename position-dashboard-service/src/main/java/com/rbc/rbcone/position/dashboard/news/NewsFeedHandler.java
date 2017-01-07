package com.rbc.rbcone.position.dashboard.news;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.rbc.rbcone.position.dashboard.model.NewsItem;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.rbc.rbcone.position.dashboard.service.NewsFeedService;

public class NewsFeedHandler extends TextWebSocketHandler {

	private static final Logger logger = LoggerFactory.getLogger(NewsFeedHandler.class);

	@Autowired
	private NewsFeedService newsFeedService;
	
	private WebSocketSession newsSession;
	private String currentTopic;
	private int count = 0;
	private final long refreshRate = 10000;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("Connection Established");
		newsSession = session;
		currentTopic = null;
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("Connection Closed");
		newsSession = null;
		currentTopic = null;
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("Message payload: " + message.getPayload());
		currentTopic = message.getPayload();
		session.sendMessage(getNewsMessage(currentTopic));
	}

	private TextMessage getNewsMessage(String topic) {
//		List<NewsItem> newsItems = newsFeedService.getNews(topic, System.currentTimeMillis() - refreshRate);
		List<NewsItem> newsItems = Arrays.asList(new NewsItem("title" + count, "http://www.google.com"), new NewsItem("title" + count++, "http://www.google.com"));
		logger.info("News items for topic: " + topic + " :" + newsItems);
		return new TextMessage(new JSONArray(newsItems).toString());
	}

	@Scheduled(fixedRate=refreshRate)
	public void checkFornews() {
		if (newsSession != null && currentTopic != null) {
			try {
				newsSession.sendMessage(getNewsMessage(currentTopic));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}