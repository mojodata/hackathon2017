package com.rbc.rbcone.position.dashboard.news;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import com.rbc.rbcone.position.dashboard.model.RssNewsItem;
import com.rometools.rome.io.FeedException;

@Service
public class RssNewsFeedService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RssNewsFeedService.class);
	
	private static final String GOOGLE_RSS_URL_TEMPLATE = "http://www.google.ca/finance/company_news?q={topic}&output=rss";
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	@Autowired
	private RssFeedParser feedParser;
	
	@Autowired
	private RestOperations rssFeedFetcher;
	
	public List<RssNewsItem> getRssNewsItem(String topic) {
		LOGGER.info("Searching RSS feeds for " + topic);
		List<RssNewsItem> newsItems = new ArrayList<>();
		try {
			//String news = fetchNewsFromGoogle(topic);
			String news = loadRssSample();
			feedParser.parseRssFeed(news, "Google Finance", newsItems);
		} catch (FeedException | IOException e) {
			e.printStackTrace();
		}
		return newsItems;
	}
	
	private String fetchNewsFromGoogle(String topic) {
		return rssFeedFetcher.getForObject(GOOGLE_RSS_URL_TEMPLATE, String.class, topic);
	}
	
	private String loadRssSample() throws IOException {
		StringBuilder rssFeed = new StringBuilder();
		try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(getClass().getResourceAsStream("/google-stock.xml")))) {
			String line = reader.readLine();
			while (line != null) {
				rssFeed.append(line);
				rssFeed.append(LINE_SEPARATOR);
				line = reader.readLine();
			}
		}
		return rssFeed.toString();
	}
}