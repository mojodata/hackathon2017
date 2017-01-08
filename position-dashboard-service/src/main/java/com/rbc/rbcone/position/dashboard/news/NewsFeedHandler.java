package com.rbc.rbcone.position.dashboard.news;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.rbc.rbcone.position.dashboard.model.RssNewsItem;
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
	
	@Scheduled(fixedRate=POLLING_RATE)
	public void checkForNews() {
		if (newsSession != null && currentTopic != null) {
			try {
				String country = "CA";
				long sinceTimeMillis = System.currentTimeMillis() - POLLING_RATE;
//				newsSession.sendMessage(new TextMessage(serializeNewsItems(newsFeedService.getNews(currentTopic, country, sinceTimeMillis)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String serializeNewsItems(List<RssNewsItem> newsItesm) {
		return null;
	}
}