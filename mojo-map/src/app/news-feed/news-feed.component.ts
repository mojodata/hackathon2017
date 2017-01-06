import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'news-feed',
    templateUrl: './news-feed.component.html'
})
export class NewsFeedComponent implements OnInit {
    news: string;

    constructor() { }

    ngOnInit() { 
        this.news = 'Some news';
    }
}