package com.rbc.rbcone.position.dashboard.service;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

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

        System.out.println(fixture.getNews(keyword, country, System.currentTimeMillis() - 100000));
    }

    @Test
    public void shouldGetNewsSummaryFromResponse() throws IOException{
        InputStream is = getClass().getClassLoader().getResourceAsStream("news.json");
        String response = IOUtils.toString(is);

        DocumentContext documentContext = JsonPath.parse(response);
        System.out.println(fixture.getNewsItems(documentContext));
    }
}
