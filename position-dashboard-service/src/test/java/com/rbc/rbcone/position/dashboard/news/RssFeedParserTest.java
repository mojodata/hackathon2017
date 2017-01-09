package com.rbc.rbcone.position.dashboard.news;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import com.rbc.rbcone.position.dashboard.model.NewsItem;

public class RssFeedParserTest {
	
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	private RssFeedParser fixture = new RssFeedParser();

	@Test
	public void testParseGoogleFinanceFeed() throws Exception {
		List<NewsItem> newsItems = new ArrayList<>();
		
		fixture.parseRssFeed(loadRssSample(), "Google", newsItems);
		
		assertEquals(10, newsItems.size());
		assertEquals("Dundee Corporation to Acquire 100% of 360 VOX Corporation by Way of Plan of ...", newsItems.get(0).getTitle());
		assertEquals("http://www.marketwired.com/press-release/dundee-corporation-acquire-100-360-vox-corporation-way-plan-arrangement-tsx-dc.a-1908942.htm", newsItems.get(0).getUrl());
		assertEquals("Google", newsItems.get(0).getSource());
		assertEquals(new GregorianCalendar(2014, Calendar.MAY, 12, 9, 30).getTime(), newsItems.get(0).getPublishedDate());
	}
	
	private String loadRssSample() throws IOException {
		StringBuilder rssFeed = new StringBuilder();
		try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(getClass().getResourceAsStream("/rss-samples/google-stock.xml")))) {
			String line = reader.readLine();
			while (line != null) {
				rssFeed.append(line);
				rssFeed.append(LINE_SEPARATOR);
				line = reader.readLine();
			}
		}
		return rssFeed.toString();
	}

}