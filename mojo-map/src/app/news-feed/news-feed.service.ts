import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';

import { $WebSocket, WebSocketSendMode } from 'angular2-websocket/angular2-websocket';

import NewsItem from '../dto/newsItem.dto';

@Injectable()
export class NewsFeedService {

    private static readonly NEWS_FEED_URL: string = 'ws://position-dashboard-service.mybluemix.net/newsfeed';
    //'ws://localhost:8089/newsfeed';

    private newsFeedSocket: $WebSocket;

    constructor() {
        this.newsFeedSocket = new $WebSocket(NewsFeedService.NEWS_FEED_URL);
        this.newsFeedSocket.setSend4Mode(WebSocketSendMode.Direct);
    }

    subscribeToNews(topic: string): Observable<any> {
        this.newsFeedSocket.send(topic);
        return this.newsFeedSocket.getDataStream();
    }

    closeFeed() {
        this.newsFeedSocket.close(true);
    }

}
