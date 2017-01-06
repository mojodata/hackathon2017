import { AfterViewInit, Input, ElementRef, ViewChild } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { OnChanges, SimpleChanges } from '@angular/core';
import Country from '../dto/country.dto';

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

  constructor() {

  }

  ngOnInit() {
  	this.options = {
  		chart: {
  			type: 'pieChart',
  			height: 450,
  			donut: true,
  			x: function(d) {return d.key;},
  			y: function(d) {return d.y;},
  			showLabels: true,
  			pie: {
  				startAngle: d => d.startAngle/2 - Math.PI/2,
  				endAngle: d => d.endAngle/2 - Math.PI/2
  			},
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
  	];
  }

  ngOnChanges(changes: SimpleChanges) {
    console.log(`Data Changes: ${JSON.stringify(changes['countries']['currentValue'])}`);
  }

}