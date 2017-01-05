package com.rbc.rbcone.position.dashboard.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wayneyu on 1/5/17.
 */
@Service
public class NewsFeedServiceImpl implements NewsFeedService {

    private static final String TOKEN = "92a7d45d-7beb-4f01-ba0f-0bb953ec7f93";
    private static final String URL_TEMPLATE = "https://webhose.io/search?token=" + TOKEN +
            "&format=json&q=language%3A(english)%20thread.country%3A%s&ts=%d";

    @Override
    public List<String> getNews(String keyword, String country) {

//        long sinceTimestamp = System.currentTimeMillis() - 10000;

//        HttpResponse<String> response = Unirest.get("https://webhose.io/search?token=92a7d45d-7beb-4f01-ba0f-0bb953ec7f93&format=json&q=language%3A(english)%20thread.country%3ACA&ts=1483557548265")
//    .header("Accept", "text/plain")
//    .asString();
        return null;
    }


}
