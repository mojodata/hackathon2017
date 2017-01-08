package com.rbc.rbcone.position.dashboard.service;

import java.util.List;

import com.rbc.rbcone.position.dashboard.model.NewsItem;

/**
 * Created by wayneyu on 1/5/17.
 */
public interface NewsFeedService {

    public List<NewsItem> getNews(String keyword, long sinceTimeMillis) throws Exception;
    
}
