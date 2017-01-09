package com.rbc.rbcone.position.dashboard.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wayneyu on 1/6/17.
 */
public class NewsItem {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
    private final String title;
    private final String url;
	private String source;
	private Date publishedDate;

    public NewsItem(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() { return title; }
    public String getUrl() { return url; }
	
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public Date getPublishedDate() {
		return publishedDate;
	}
	
	public String getFormattedPublishedDate() {
		return publishedDate == null ? "" : DATE_FORMAT.format(publishedDate);
	}
	
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

    @Override
    public String toString() {
        return "[title: " + title + ", url:" + url + "]";
    }
}
