package com.rbc.rbcone.position.dashboard.model;

/**
 * Created by wayneyu on 1/6/17.
 */
public class NewsItem {

    private final String title;
    private final String url;

    public NewsItem(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() { return title; }
    public String getUrl() { return url; }

    @Override
    public String toString() {
        return "[title: " + title + ", url:" + url + "]";
    }
}
