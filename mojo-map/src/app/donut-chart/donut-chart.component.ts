import { AfterViewInit, Input, ElementRef, ViewChild } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { OnChanges, SimpleChanges } from '@angular/core';
import Country from '../dto/country.dto';
import DonutDataPoint from '../dto/donutDataPoint.dto';
import 'rxjs/add/operator/map';

declare let d3: any;

@Component({
  selector: 'app-donut-chart',
  templateUrl: './donut-chart.component.html',
  styleUrls: ['./donut-chart.component.css']
})
export class DonutChartComponent implements OnInit, OnChanges {

  @Input()
  countries: Array<Country>;

	options: any;
	data: any;

  donutRatio = 0.9;

  constructor() {

  }

  ngOnInit() {
  	this.options = {
  		chart: {
  			type: 'pieChart',
        height: 900,
  			donut: true,
        donutRatio: 0.5,
  			x: function(d) {return d.label;},
  			y: function(d) {return d.value;},
  			showLabels: true,
  			pie: {
  				startAngle: d => (d.startAngle - Math.PI) * this.donutRatio, 
  				endAngle: d => (d.endAngle - Math.PI) * this.donutRatio 
  			},
        labelType: "percent",
  			duration: 500,
  			legend: {
  				margin: {
  					top: 5,
  					right: 140,
  					bottom: 5,
  					left: 0
  				}
  			}
  		}
  	};

  	this.data = [
/*
  		{
  			key: "One",
  			y: 5
  		},
  		{
  			key: "Two",
  			y: 2
  		},
  		{
  			key: "Three",
  			y: 9
  		},
  		{
  			key: "Four",
  			y: 7
  		},
  		{
  			key: "Five",
  			y: 4 
  		},
  		{
  			key: "Six",
  			y: 3
  		},
  		{
  			key: "Seven",
  			y: 0.5
  		}
*/
  	];
  }

  ngOnChanges(changes: SimpleChanges) {
    // console.log(`Data Changes: ${JSON.stringify(changes['countries']['currentValue'])}`);
    if (this.countries) {
      this.data = this.countries.map(aCountry => new DonutDataPoint(aCountry.code, aCountry.marketValue));
    }
  }

}