package com.rbc.rbcone.position.dashboard.service;

import org.springframework.stereotype.Service;

import com.mashape.unirest.http.Unirest;

/**
 * Created by wayneyu on 1/5/17.
 */
@Service
public class NewsFeedServiceImpl implements NewsFeedService {

    private static final String TOKEN = "92a7d45d-7beb-4f01-ba0f-0bb953ec7f93";
    private static final String URL = "https://webhose.io/search";
    private static final String QUERY = "token=%s&format=json&q=%s language:(english) thread.country:%s&ts=%d";
    
    private int counter;

    @Override
    public String getNews(String keyword, String countryCode) throws Exception {
        long sinceTimestamp = System.currentTimeMillis() - 300000;

        String query = String.format(QUERY, TOKEN, keyword, countryCode, sinceTimestamp);
        return Unirest.get(URL + "?" + query.replaceAll(" ", "%20").replaceAll(":", "%3A"))
                .header("Accept", "text/plain")
                .asString()
                .getBody();
    }
    
    @Override
	public String fakeNews() {
    	return "News " + counter++;
    }

}
