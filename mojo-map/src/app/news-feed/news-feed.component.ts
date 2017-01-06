import { Component, OnInit, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs/Observable';

@Component({
    selector: 'news-feed',
    templateUrl: './news-feed.component.html'
})
export class NewsFeedComponent implements OnInit, OnDestroy {

    news: string;

    ngOnInit() {
        this.news = 'Some news';
    }

    ngOnDestroy() {
    }
}