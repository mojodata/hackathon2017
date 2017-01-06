package com.rbc.rbcone.position.dashboard.service;

import com.rbc.rbcone.position.dashboard.model.NewsItem;

import java.util.List;

/**
 * Created by wayneyu on 1/5/17.
 */
public interface NewsFeedService {

    public List<NewsItem> getNews(String keyword, String country, long sinceTimeMillis) throws Exception;

    public String fakeNews();
}
