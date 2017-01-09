package com.rbc.rbcone.position.dashboard.model;

import java.util.Date;

public class RssNewsItem extends NewsItem {
	
	private String source;
	private Date publishedDate;

	public RssNewsItem(String title, String url) {
		super(title, url);
	}
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public Date getPublishedDate() {
		return publishedDate;
	}
	
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
}