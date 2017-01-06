import { Component, OnInit } from '@angular/core';
import { Input } from '@angular/core';

@Component({
  selector: 'app-holdings-table',
  templateUrl: './holdings-table.component.html',
  styleUrls: ['./holdings-table.component.css']
})
export class HoldingsTableComponent implements OnInit {

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

  constructor() { }

  ngOnInit() {
  }

}
