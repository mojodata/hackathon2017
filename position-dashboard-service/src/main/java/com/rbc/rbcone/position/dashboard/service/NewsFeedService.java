package com.rbc.rbcone.position.dashboard.service;

/**
 * Created by wayneyu on 1/5/17.
 */
public interface NewsFeedService {

    public String getNews(String keyword, String country) throws Exception;
    
    public String fakeNews();
}
