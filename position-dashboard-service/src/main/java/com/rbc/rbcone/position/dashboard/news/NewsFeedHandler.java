package com.rbc.rbcone.position.dashboard.news;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.rbc.rbcone.position.dashboard.model.NewsItem;
import com.rbc.rbcone.position.dashboard.service.NewsFeedService;

public class NewsFeedHandler extends TextWebSocketHandler {

	private static final Logger logger = LoggerFactory.getLogger(NewsFeedHandler.class);
	
	private static final long POLLING_RATE = 60000;
	
	@Autowired
	private NewsFeedService newsFeedService;
	
	@Autowired
	private RssNewsFeedService rssNewsFeedService;
	
	private WebSocketSession newsSession;
	private String currentTopic;
	private int count;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("WebSocket Connection Established");
		newsSession = session;
		currentTopic = null;
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.info("WebSocket Connection Closed");
		newsSession = null;
		currentTopic = null;
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("Message payload: " + message.getPayload());
		currentTopic = message.getPayload();
		TextMessage newsMessage = getNewsMessage(currentTopic);
		if (newsMessage != null) {
			session.sendMessage(newsMessage);
		}
	}

	private TextMessage getNewsMessage(String topic) throws Exception {
		List<NewsItem> newsItems = new ArrayList<>();
//		newsItems.addAll(newsFeedService.getNews(topic, System.currentTimeMillis() - POLLING_RATE));
//		List<NewsItem> newsItems = Arrays.asList(new NewsItem("title" + count, "http://www.google.com"), new NewsItem("title" + count++, "http://www.google.com"));
		newsItems.addAll(rssNewsFeedService.getRssNewsItem(topic));
		logger.info("News items for topic: " + topic + " :" + newsItems);
		return newsItems.isEmpty() ? null : new TextMessage(new JSONArray(newsItems).toString());
	}
	
	@Scheduled(fixedRate=POLLING_RATE)
	public void checkForNews() {
		if (newsSession != null && currentTopic != null) {
			try {
				TextMessage newsMessage = getNewsMessage(currentTopic);
				if (newsMessage != null) {
					newsSession.sendMessage(newsMessage);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}