package com.rbc.rbcone.position.dashboard.service;

import java.util.List;

/**
 * Created by wayneyu on 1/5/17.
 */
public interface NewsFeedService {

    public List<String> getNews(String keyword, String country);
}
