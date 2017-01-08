import { Component, OnInit, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { LocalDataSource } from 'ng2-smart-table';

import { NewsFeedItem } from './news-feed-item';
import { NewsFeedService } from './news-feed.service';
import { MockNewsFeedService } from './news-feed-mock.service';

import NewsItem from '../dto/newsItem.dto';

@Component({
    selector: 'news-feed',
    templateUrl: './news-feed.component.html'
})
export class NewsFeedComponent implements OnInit, OnDestroy {

<<<<<<< HEAD
    private static readonly MAX_NEWS_ITEMS = 10;

    topic: string;
    newsItems: NewsFeedItem[];
	settings = {
		columns: {
			source: {
				title: 'Source'
			},
			timestamp: {
				title: 'Timestamp',
                sortDirection: 'desc'
			},
			summary: {
				title: 'Summary'
			},
			link: {
				title: 'Link',
                type: 'html'
			}
		},
		editable: false,
		actions: {
			add: false,
			edit: false,
			delete: false
		}
	};

	source: LocalDataSource;
=======
    topic: string;
>>>>>>> refs/remotes/origin/master

    constructor(private newsFeedService: NewsFeedService) { }

    private newsItems: NewsItem[];

    ngOnInit() {
<<<<<<< HEAD
        this.topic = 'Some topic';
        this.newsItems = [];
		this.newsItems = MockNewsFeedService.getFakeNewsItems();
  		this.source = new LocalDataSource(this.newsItems);
        // this.newsFeedService
        //     .subscribeToNews(this.topic)
        //     .subscribe(
        //         newsItem => {
        //             this.newsItems.push(newsItem.data);
        //         },
        //         error => {
        //             console.log('Error: ' + error.message); 
        //         },
        //         () => {
        //             console.log('Completed');
        //         }
        //     );
=======
        this.topic = 'Canada';
        let subscription = this.newsFeedService.subscribeToNews(this.topic).subscribe(
          res => this.newsItems = JSON.parse(res.data),
          e => { console.log('Error: ' + e.message); },
          () => { console.log('Completed'); }
        );
>>>>>>> refs/remotes/origin/master
    }

    ngOnDestroy() {
        // this.newsFeedService.closeFeed();
    }
}
