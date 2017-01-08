package com.rbc.rbcone.position.dashboard.model;

import java.util.Date;

public class RssNewsItem extends NewsItem {
	
	private String source;
	private Date timestamp;

	public RssNewsItem(String title, String url) {
		super(title, url);
	}
	
	public String getSource() {
		return source;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
}