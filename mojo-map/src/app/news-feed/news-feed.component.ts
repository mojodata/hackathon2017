import { Component, OnInit, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import { NewsFeedService } from './news-feed.service';

import NewsItem from '../dto/newsItem.dto';

@Component({
    selector: 'news-feed',
    templateUrl: './news-feed.component.html'
})
export class NewsFeedComponent implements OnInit, OnDestroy {

    topic: string;

    constructor(private newsFeedService: NewsFeedService) { }

    private newsItems: NewsItem[];

    ngOnInit() {
        this.topic = 'Canada';
        let subscription = this.newsFeedService.subscribeToNews(this.topic).subscribe(
          res => this.newsItems = JSON.parse(res.data),
          e => { console.log('Error: ' + e.message); },
          () => { console.log('Completed'); }
        );
    }

    ngOnDestroy() {
    }
}
