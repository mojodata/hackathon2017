package com.rbc.rbcone.position.dashboard.news;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private static final long POLLING_RATE = 3600000;
	
	@Autowired
	private NewsFeedService newsFeedService;
	
	@Autowired
	private RssNewsFeedService rssNewsFeedService;

	private Map<String, NewsFeedSessionState> sessionIdToStateMap = new HashMap<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("WebSocket Connection Established: " + session.getId());
		NewsFeedSessionState state = new NewsFeedSessionState();
		state.setSession(session);
		sessionIdToStateMap.put(session.getId(), state);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.info("WebSocket Connection Closed: " + session.getId());
		sessionIdToStateMap.remove(session.getId());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("Message payload: " + message.getPayload());
		NewsFeedSessionState sessionState = sessionIdToStateMap.get(session.getId());
		sessionState.setCurrentTopic(message.getPayload());
		TextMessage newsMessage = getNewsMessage(sessionState);
		if (newsMessage != null) {
			session.sendMessage(newsMessage);
		}
	}

	private TextMessage getNewsMessage(NewsFeedSessionState sessionState) throws Exception {
		List<NewsItem> newsItems = new ArrayList<>();
		newsItems.addAll(newsFeedService.getNews(sessionState.getCurrentTopic(), System.currentTimeMillis() - POLLING_RATE));
		newsItems.addAll(rssNewsFeedService.getRssNewsItem(sessionState));
		logger.info("News items for topic: " + sessionState.getCurrentTopic() + " :" + newsItems.size());
		return newsItems.isEmpty() ? null : new TextMessage(new JSONArray(newsItems).toString());
	}
	
	@Scheduled(fixedRate=POLLING_RATE)
	public void checkForNews() {
		for (String sessionId : sessionIdToStateMap.keySet()) {
			NewsFeedSessionState state = sessionIdToStateMap.get(sessionId);
			if (state != null && state.getCurrentTopic() != null) {
				try {
					TextMessage newsMessage = getNewsMessage(state);
					if (newsMessage != null) {
						state.getSession().sendMessage(newsMessage);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}