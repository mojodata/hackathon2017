package com.rbc.rbcone.position.dashboard.service;

import org.junit.Before;
import org.junit.Test;

import java.net.URLEncoder;

/**
 * Created by wayneyu on 1/6/17.
 */
public class NewsFeedServiceImplTest {

    private NewsFeedServiceImpl fixture;

    @Before
    public void setup(){
        fixture = new NewsFeedServiceImpl();
    }

    @Test
    public void shouldGetNewsFeedForCountry() throws Exception {
        String keyword = "Tesla";
        String country = "US";

        System.out.println(fixture.getNews(keyword, country));
    }
}
