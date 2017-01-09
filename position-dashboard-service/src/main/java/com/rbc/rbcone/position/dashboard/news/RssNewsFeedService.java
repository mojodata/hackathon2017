package com.rbc.rbcone.position.dashboard.news;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import com.rbc.rbcone.position.dashboard.model.NewsItem;
import com.rometools.rome.io.FeedException;

@Service
public class RssNewsFeedService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RssNewsFeedService.class);
	
	@Autowired
	private NewsFeedSources sources;
	
	@Autowired
	private RssFeedParser feedParser;
	
	@Autowired
	private RestOperations rssFeedFetcher;
	
	public List<NewsItem> getRssNewsItem(NewsFeedSessionState sessionState) {
		LOGGER.info("Searching RSS feeds for " + sessionState.getCurrentTopic());
		List<NewsItem> newsItems = new ArrayList<>();
		for (NewsFeedSource source : sources.getSources()) {
			try {
				String news = fetchRssFeed(sessionState, source);
				LOGGER.info(news);
				feedParser.parseRssFeed(news, source.getName(), newsItems);
				newsItems = filterOldNews(sessionState, newsItems);
			} catch (FeedException | IOException e) {
				LOGGER.error("Failed to get news from " + source.getName(), e);
			}
		}
		return newsItems;
	}

	private String fetchRssFeed(NewsFeedSessionState sessionState, NewsFeedSource source) {
		LOGGER.info("Fetching news for source " + source.getName());
		return source.getUrl().indexOf("{topic}") >=0 ?
				rssFeedFetcher.getForObject(source.getUrl(), String.class, sessionState.getCurrentTopic()) :
					rssFeedFetcher.getForObject(source.getUrl(), String.class);
	}

	private List<NewsItem> filterOldNews(NewsFeedSessionState sessionState, List<NewsItem> newsItems) {
		List<NewsItem> filteredItems = new ArrayList<>();
		for (NewsItem rssNewsItem : newsItems) {
			if (sessionState.getLatestNewsDate() != null) {
				if (rssNewsItem.getPublishedDate() != null && rssNewsItem.getPublishedDate().after(sessionState.getLatestNewsDate())) {
					filteredItems.add(rssNewsItem);
				}
			} else {
				filteredItems.add(rssNewsItem);
			}
		}
		for (NewsItem rssNewsItem : newsItems) {
			if (sessionState.getLatestNewsDate() == null || 
						(rssNewsItem.getPublishedDate() != null && rssNewsItem.getPublishedDate().after(sessionState.getLatestNewsDate()))) {
				sessionState.setLatestNewsDate(rssNewsItem.getPublishedDate());
			}
		}
		return filteredItems;
	}
}