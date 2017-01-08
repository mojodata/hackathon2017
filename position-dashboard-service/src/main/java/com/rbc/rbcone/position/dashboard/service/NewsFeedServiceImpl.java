package com.rbc.rbcone.position.dashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.mashape.unirest.http.Unirest;
import com.rbc.rbcone.position.dashboard.model.NewsItem;

import net.minidev.json.JSONArray;


/**
 * Created by wayneyu on 1/5/17.
 */
@Service
public class NewsFeedServiceImpl implements NewsFeedService {

    private static final String TOKEN = "92a7d45d-7beb-4f01-ba0f-0bb953ec7f93";
    private static final String URL = "https://webhose.io/search";
    private static final String QUERY = "token=%s&format=json&q=%s language:(english) thread.country:%s&ts=%d";
    
    @Override
    public List<NewsItem> getNews(String keyword, String countryCode, long sinceTimeMillis) throws Exception {
        String query = String.format(QUERY, TOKEN, keyword, countryCode, sinceTimeMillis);

        String jsonResponse = Unirest.get(URL + "?" + query.replaceAll(" ", "%20").replaceAll(":", "%3A"))
                .header("Accept", "text/plain")
                .asString()
                .getBody();

        return getNewsItems(JsonPath.parse(jsonResponse));
    }

    List<String> getTitles(DocumentContext documentContext) {
        return getFields(documentContext, "$..thread.title");
    }

    List<String> getUrls(DocumentContext documentContext) {
        return getFields(documentContext, "$..thread.url");
    }

    private List<String> getFields(DocumentContext documentContext, String jsonPath) {
        List<String> fields = new ArrayList<>();
        for (Object field : documentContext.<JSONArray>read(jsonPath)) {
            fields.add((String) field);
        }
        return fields;
    }

    List<NewsItem> getNewsItems(DocumentContext documentContext) {
        List<String> urls = getUrls(documentContext);
        List<String> titles = getTitles(documentContext);
        List<NewsItem> items = new ArrayList<>();

        for(int i = 0; i < titles.size(); i++) {
            items.add(new NewsItem(titles.get(i), urls.get(i)));
        }

        return items;
    }

}
