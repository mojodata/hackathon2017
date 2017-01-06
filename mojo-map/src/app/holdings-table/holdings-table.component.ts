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

	@Input()
	totalMarketValue: number;

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
				title: 'Price',
				valuePrepareFunction: (value) => {
					return `$${value}`;
				}
			},
			marketBaseValue: {
				title: 'Market Base Value',
				valuePrepareFunction: (value) => {
					return `$${value}`;
				}
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
