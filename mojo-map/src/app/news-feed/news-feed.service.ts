import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';

import { $WebSocket, WebSocketSendMode } from 'angular2-websocket/angular2-websocket';

@Injectable()
export class NewsFeedService {

    private static readonly NEWS_FEED_URL: string = 'ws://localhost:8080/newsfeed';

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