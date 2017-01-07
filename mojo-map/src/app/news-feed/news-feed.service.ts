import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';

import { $WebSocket, WebSocketSendMode } from 'angular2-websocket/angular2-websocket';

import NewsItem from '../dto/newsItem.dto';

@Injectable()
export class NewsFeedService {

    private static readonly NEWS_FEED_URL: string = 'ws://position-dashboard-service.mybluemix.net:8089/newsfeed';
    //'ws://localhost:8089/newsfeed';

    private newsFeed: $WebSocket;

    constructor() {
        this.newsFeed = new $WebSocket(NewsFeedService.NEWS_FEED_URL);
        this.newsFeed.setSend4Mode(WebSocketSendMode.Direct);
    }

    subscribeToNews(topic: string): Observable<any> {
        this.newsFeed.send(topic);
        return this.newsFeed.getDataStream();
    }

}
