package com.rbc.rbcone.position.dashboard.news;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="newsfeed")
public final class NewsFeedSources {
	
	private List<NewsFeedSource> sources;
	
	public List<NewsFeedSource> getSources() {
		return sources;
	}
	
	public void setSources(List<NewsFeedSource> sources) {
		this.sources = sources;
	}
}