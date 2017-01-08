package com.rbc.rbcone.position.dashboard.news;

import java.io.InputStream;

import org.springframework.stereotype.Component;

@Component
public class FilebasedRssNewsFeedFetcher implements RssNewsFeedFetcher {

	@Override
	public InputStream getRssNewsStream() {
		return getClass().getResourceAsStream("/rss-samples/google-stock.xml");
	}

}