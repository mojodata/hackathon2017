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
			formattedPublishedDate: {
				title: 'Published Date'
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

    ngOnInit() {
        this.topic = 'Some topic';
  		this.source = new LocalDataSource();
        this.newsFeedService
            .subscribeToNews(this.topic)
            .subscribe(
                newsItem => {
                    let items: NewsFeedItem[] = JSON.parse(newsItem.data);
                    items.forEach(item => {
                        item.url = '<a href="' + item.url + '">' + item.url + '</a>';
                        this.source.prepend(item);
                    });
                    this.source.setSort([{ field: 'formattedPublishedDate', direction: 'desc' }]);
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