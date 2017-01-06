import { Component, OnInit, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import { NewsFeedService } from './news-feed.service';

@Component({
    selector: 'news-feed',
    templateUrl: './news-feed.component.html'
})
export class NewsFeedComponent implements OnInit, OnDestroy {

    news: string;

    constructor(private newsFeedService: NewsFeedService) { }

    ngOnInit() {
        this.news = 'Some news';
        this.newsFeedService.subscribeToNews(this.news).subscribe(
            res => {
                console.log('Data: ' + res.data);
            },
            function (e) { console.log('Error: ' + e.message); },
            function () { console.log('Completed'); }
        );
    }

    ngOnDestroy() {
    }
}