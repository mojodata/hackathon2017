package com.rbc.rbcone.position.dashboard.news;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.rbcone.position.dashboard.model.RssNewsItem;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@Service
public class RssNewsFeedService {
	
	@Autowired
	private RssNewsFeedFetcher newsFetcher;
	
	public List<RssNewsItem> getRssNewsItem(String topic) {
		System.out.println("Searching RSS feeds for " + topic);
		List<RssNewsItem> items = new ArrayList<>();
		InputStream stream = newsFetcher.getRssNewsStream();
		try {
			parseFeed(stream);
		} catch (FeedException | IOException e) {
			e.printStackTrace();
		} finally {
			closeStreamQuietly(stream);
		}
		return items;
	}
	
	private void parseFeed(InputStream stream) throws FeedException, IOException {
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(stream));
		System.out.println(feed.getTitle());
	}
	
	private void closeStreamQuietly(InputStream stream) {
		try {
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}