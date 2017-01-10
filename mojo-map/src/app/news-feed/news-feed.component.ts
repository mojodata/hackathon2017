import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import { LocalDataSource } from 'ng2-smart-table';

import { NewsFeedItem } from './news-feed-item';
import { NewsFeedService } from './news-feed.service';

import { TypeaheadMatch } from 'ng2-bootstrap';

@Component({
    selector: 'news-feed',
    templateUrl: './news-feed.component.html',
    styleUrls: ['./news-feed.component.css']
})
export class NewsFeedComponent implements OnInit, OnDestroy {

    public feedCtrl: FormControl = new FormControl();

    public feedForm: FormGroup = new FormGroup({
        topic: this.feedCtrl
    });

    topic: string;

	settings = {
		columns: {
            url: {
                title: 'Feeds',
                type: 'html'
            },
			formattedPublishedDate: {
				title: 'Published'
			},
			
		},
		editable: false,
		actions: {
			add: false,
			edit: false,
			delete: false
		}
	};

    topics = ['RBC', 'HSBC', 'TD', 'CIBC', 'BMO'];

	source: LocalDataSource;

    constructor(private newsFeedService: NewsFeedService) { }

    ngOnInit() {
        // this.topic = 'RBC';
        this.source = new LocalDataSource();
        this.getFeed('RBC');
    }

    ngOnDestroy() {
        this.newsFeedService.closeFeed();
    }

    public typeaheadOnSelect(e: TypeaheadMatch): void {
        console.log('Selected value: ', e.value);
        this.getFeed(e.value);
    }

    onSubmit() {
        this.topic = this.feedForm.value;
        this.source = new LocalDataSource();
        this.newsFeedService
            .subscribeToNews(this.topic)
            .subscribe(
                newsItem => {
                    let items: NewsFeedItem[] = JSON.parse(newsItem.data);
                    items.forEach(item => {
                        item.url = '<a href="' + item.url + '" target="_blank">' + item.title + '</a>';
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

    getFeed(feed) {
        this.topic = feed;
        this.newsFeedService
            .subscribeToNews(this.topic)
            .subscribe(
                newsItem => {
                    let items: NewsFeedItem[] = JSON.parse(newsItem.data);
                    items.forEach(item => {
                        item.url = '<a href="' + item.url + '" target="_blank">' + item.title + '</a>';
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
}