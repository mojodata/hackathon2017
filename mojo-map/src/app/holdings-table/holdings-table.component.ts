import { Component, OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { OnChanges, SimpleChanges } from '@angular/core';
import { LocalDataSource } from 'ng2-smart-table';

@Component({
  selector: 'app-holdings-table',
  templateUrl: './holdings-table.component.html',
  styleUrls: ['./holdings-table.component.css']
})
export class HoldingsTableComponent implements OnInit, OnChanges {

	@Input()
	data: any;

	settings = {
		columns: {
			securityDescription: {
				title: 'Security Description'
			},
			securityId: {
				title: 'Security ID'
			},
			units: {
				title: 'Units'
			},
			price: {
				title: 'Price'
			},
			marketBaseValue: {
				title: 'Market Base Value'
			},
			majorSecurityType: {
				title: 'Major Security Type'
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

  constructor() {
  }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['data'].currentValue) {
  		this.source = new LocalDataSource(this.data);
    }
  }

  onSearch(query: string = ''): void {
  	this.source.setFilter([
  		{
  			field: 'securityDescription',
  			search: query
  		},
  		{
  			field: 'securityId',
  			search: query
  		},
  		{
  			field: 'units',
  			search: query
  		},
  		{
  			field: 'price',
  			search: query
  		},
  		{
  			field: 'marketBaseValue',
  			search: query
  		},
  		{
  			field: 'majorSecurityType',
  			search: query
  		}
  	], false);

  }

}
