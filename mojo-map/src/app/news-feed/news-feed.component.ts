import { Component, OnInit, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { $WebSocket } from 'angular2-websocket/angular2-websocket';

@Component({
    selector: 'news-feed',
    templateUrl: './news-feed.component.html'
})
export class NewsFeedComponent implements OnInit, OnDestroy {

    news: string;
    private websocket: $WebSocket;

    ngOnInit() {
        this.news = 'Some news';
        this.websocket = new $WebSocket('ws://localhost:8080/newsfeed', null, {initialTimeout: 500, maxTimeout: 300000, reconnectIfNotNormalClose: true});
        this.websocket.send('foo').subscribe(
            (msg) => {
                console.log("next", msg.data);
            },
            (msg) => {
                console.log("error", msg);
            },
            () => {
                console.log("complete");
            }
        );
        this.websocket.send
    }

    ngOnDestroy() {
        this.websocket.close(true);
    }
}