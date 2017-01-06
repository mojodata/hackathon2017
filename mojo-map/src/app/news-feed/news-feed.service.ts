import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';

import { $WebSocket, WebSocketSendMode } from 'angular2-websocket/angular2-websocket';

@Injectable()
export class NewsFeedService {

    private static readonly NEWS_FEED_URL: string = 'ws://localhost:8080/newsfeed';

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