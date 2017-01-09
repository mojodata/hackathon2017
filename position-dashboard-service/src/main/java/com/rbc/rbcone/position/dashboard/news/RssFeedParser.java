package com.rbc.rbcone.position.dashboard.news;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.stereotype.Component;

import com.rbc.rbcone.position.dashboard.model.NewsItem;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@Component
final class RssFeedParser {
	
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	void parseRssFeed(String rssNews, String source, List<NewsItem> newsItems) throws FeedException, IOException {
		SyndFeedInput input = new SyndFeedInput();
		try (ByteArrayInputStream newsStream = new ByteArrayInputStream(removeDescriptions(rssNews).getBytes())) {
			try (XmlReader xmlReader = new XmlReader(newsStream)) {
				SyndFeed feed = input.build(xmlReader);
				for (SyndEntry entry : feed.getEntries()) {
					NewsItem newsItem = new NewsItem(normalizeTitle(entry.getTitle()), entry.getLink().trim());
					newsItem.setSource(source);
					newsItem.setPublishedDate(entry.getPublishedDate());
					newsItems.add(newsItem);
				}
			}
		}
	}
	
	private String normalizeTitle(String rawTitle) {
		StringBuilder normalizedTitle = new StringBuilder();
		StringTokenizer tokenizer = new StringTokenizer(rawTitle);
		boolean needsSpace = false;
		while (tokenizer.hasMoreTokens()) {
			if (!needsSpace) {
				needsSpace = true;
			} else {
				normalizedTitle.append(" ");
			}
			normalizedTitle.append(tokenizer.nextToken());
		}
		return normalizedTitle.toString();
	}
	
	//Remove descriptions as they many contain html that will cause the parser to fail
	private String removeDescriptions(String rssNews) {
		StringBuilder trimmedFeed = new StringBuilder();
		String[] lines = rssNews.split(LINE_SEPARATOR);
		boolean inDescription = false;
		for (String line : lines) {
			if (line.indexOf("<description>") >= 0) {
				inDescription = true;
			} 
			
			if (!inDescription) {
				trimmedFeed.append(line);
			}
			
			if (line.indexOf("</description>") >= 0) {
				inDescription = false;
			}
		}
		return trimmedFeed.toString();
	}
}
