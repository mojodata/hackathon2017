import { Component, OnInit, Input } from '@angular/core';
import { MapColorService } from '../map-color.service';
import 'rxjs/add/operator/map'; 

@Component({
  selector: 'app-color-bar',
  templateUrl: './color-bar.component.html',
  styleUrls: ['./color-bar.component.css']
})
export class ColorBarComponent implements OnInit {

	colors: Array<any>;

	@Input()
	accountDetail: any;

  constructor(private mapColorService: MapColorService) { }

  ngOnInit() {
  	this.colors = this.mapColorService.getColorRankArray().map(aColor => { 
  			return {"background-color":aColor};
  	});
  }

}