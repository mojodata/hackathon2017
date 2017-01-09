package com.rbc.rbcone.position.dashboard.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.rbc.rbcone.position.dashboard.model.NewsItem;

import net.minidev.json.JSONArray;


/**
 * Created by wayneyu on 1/5/17.
 */
@Service
public class NewsFeedServiceImpl implements NewsFeedService {

	private static final Logger logger = LoggerFactory.getLogger(NewsFeedServiceImpl.class);
	
	private static final SimpleDateFormat PUBLISH_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    private static final String TOKEN = "92a7d45d-7beb-4f01-ba0f-0bb953ec7f93";
    private static final String URL = "https://webhose.io/search";
    private static final String QUERY = "token=%s&format=json&q=%s language:(english)&ts=%d";
    
    @Override
    public List<NewsItem> getNews(String keyword, long sinceTimeMillis) {
        String query = String.format(QUERY, TOKEN, keyword, sinceTimeMillis);

        String jsonResponse = "";
        try {
            logger.info("Making news query: " + query);
            jsonResponse = Unirest.get(URL + "?" + query.replaceAll(" ", "%20").replaceAll(":", "%3A"))
                    .header("Accept", "text/plain")
                    .asString()
                    .getBody();
            logger.debug("News response: " + jsonResponse);
        } catch (UnirestException ex) {
            logger.error("Error making http request.", ex);
        }

        DocumentContext documentContext = JsonPath.parse(jsonResponse);
        logger.info("Requests left" + getFields(documentContext, "$..requestsLeft"));
		return getNewsItems(documentContext);
    }

    List<String> getTitles(DocumentContext documentContext) {
        return getFields(documentContext, "$..thread.title");
    }

    List<String> getUrls(DocumentContext documentContext) {
        return getFields(documentContext, "$..thread.url");
    }

    List<String> getPublishDates(DocumentContext documentContext) {
        return getFields(documentContext, "$..thread.published");
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
        List<String> publishDates = getPublishDates(documentContext);
        List<NewsItem> items = new ArrayList<>();

        for(int i = 0; i < titles.size(); i++) {
            NewsItem newsItem = new NewsItem(titles.get(i), urls.get(i));
            newsItem.setSource("webhose.io");
            try {
				newsItem.setPublishedDate(PUBLISH_DATE_FORMAT.parse(publishDates.get(i)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			items.add(newsItem);
        }

        return items;
    }

}
