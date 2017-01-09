import { Component, OnInit, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { LocalDataSource } from 'ng2-smart-table';

import { NewsFeedItem } from './news-feed-item';
import { NewsFeedService } from './news-feed.service';

@Component({
    selector: 'news-feed',
    templateUrl: './news-feed.component.html'
})
export class NewsFeedComponent implements OnInit, OnDestroy {

    topic: string;
	settings = {
		columns: {
			source: {
				title: 'Source'
			},
			publishedDate: {
				title: 'Published Date',
                sortDirection: 'desc'
			},
			title: {
				title: 'Title'
			},
			url: {
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

    constructor(private newsFeedService: NewsFeedService) { }

    private newsItems: NewsFeedItem[];

    ngOnInit() {
        this.topic = 'Some topic';
        this.newsItems = [];
  		this.source = new LocalDataSource(this.newsItems);
        this.newsFeedService
            .subscribeToNews(this.topic)
            .subscribe(
                newsItem => {
                    let items: NewsFeedItem[] = JSON.parse(newsItem.data);
                    items.forEach(item => {
                        item.url = '<a href="' + item.url + '">' + item.url + '</a>';
                        console.log(item.url);
                        this.newsItems.push(item);
                    });
                    this.source = new LocalDataSource(this.newsItems);
                },
                error => {
                    console.log('Error: ' + error.message); 
                },
                () => {
                    console.log('Completed');
                }
            );
    }

    ngOnDestroy() {
        this.newsFeedService.closeFeed();
    }
}