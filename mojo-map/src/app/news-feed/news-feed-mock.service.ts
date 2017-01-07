import { Injectable } from '@angular/core';

import { NewsFeedItem } from './news-feed-item';

@Injectable()
export class MockNewsFeedService {

    private static readonly SOURCES: string[] = [
        'Bloomberg',
        'Reuters'
    ];

    private static readonly LINKS: string[] = [
        'http://www.bloomberg.com',
        'http://www.reuters.com'
    ];

    private static readonly TIMESTAMPS: string[] = [
        '2017-01-01 12:34',
        '2016-12-31 01:00',
        '2013-06-21 17:45'
    ];

    static getFakeNewsItems(): NewsFeedItem[] {
        let items: NewsFeedItem[] = [];
        for (let i = 0; i < 50; i++) {
            let source = MockNewsFeedService.SOURCES[i % MockNewsFeedService.SOURCES.length];
            let link = MockNewsFeedService.LINKS[i % MockNewsFeedService.LINKS.length];
            let timestamp = MockNewsFeedService.TIMESTAMPS[i % MockNewsFeedService.TIMESTAMPS.length];
            items.push(MockNewsFeedService.createNewsItem(source, timestamp, 'Summary ' + i, link));
        };
        return items;
    }

    private static createNewsItem(source: string, timestamp: string, summary: string, link: string): NewsFeedItem {
        let item: NewsFeedItem = new NewsFeedItem();
        item.source = source;
        item.timestamp = timestamp;
        item.summary = summary;
        item.link = '<a href="' + link + '" >' + link + '</a>';
        return item;
    }
    
}