package com.rbc.rbcone.position.dashboard.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by wayneyu on 1/6/17.
 */
public class NewsFeedServiceImplTest {

    private NewsFeedServiceImpl fixture;

    @Before
    public void setup(){
        fixture = new NewsFeedServiceImpl();
    }

    @Ignore
    public void shouldGetNewsFeedForCountry() throws Exception {
        String keyword = "Tesla";
        String country = "US";

        System.out.println(fixture.getNews(keyword, country));
    }
}
