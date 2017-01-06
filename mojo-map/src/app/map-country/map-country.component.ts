import { ChangeDetectionStrategy, Component, OnInit, ViewChild, Input } from '@angular/core';
import { Output, EventEmitter } from '@angular/core';
import { OnChanges, SimpleChanges } from '@angular/core';
import { ModalDirective } from 'ng2-bootstrap';
import { CoordinateService } from '../coordinate.service';
import { Observable } from 'rxjs/Observable';

import { LatLng, LatLngLiteral, PolygonManager, SebmGoogleMapPolygon } from 'angular2-google-maps/core';
import { PolyMouseEvent } from 'angular2-google-maps/core';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';

@Component({
  selector: 'app-map-country',
  templateUrl: './map-country.component.html',
  styleUrls: ['./map-country.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class MapCountryComponent implements OnInit, OnChanges {

  @Input()
  countries: Array<string>;


  @Output()
  countryChange = new EventEmitter<string>();


	private data: Observable<Array<Array<LatLngLiteral>>>;
	private anyErrors: boolean;
  private finished: boolean;
  private polygons = new Array<SebmGoogleMapPolygon>();

  constructor(
                private coordinateService: CoordinateService
                , private polygonManager: PolygonManager
  ) {}

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {

    while (this.polygons.length !== 0) {
      let aPolygon: SebmGoogleMapPolygon = this.polygons.pop();
      this.coordinateService.removeRegion(this.polygonManager, aPolygon);
    }

    if (changes['countries'].currentValue) {
      changes['countries'].currentValue.forEach(aCountry => {
        let subscription = this.coordinateService.getRegions(aCountry).subscribe(
          paths => paths.forEach((aPath) => {
              let addedPolygon = this.coordinateService.addRegion(
                                                                    this.polygonManager,
                                                                    aPath,
                                                                    "#FFFF00",
                                                                    aCountry,
                                                                    this.onMouseClick,
                                                                    this);
              this.polygons.push(addedPolygon);
          }),
          error => this.anyErrors = true,
          () => this.finished = true
        );
      });
    }
  }

  onMouseOver(event: PolyMouseEvent) {
    console.debug('MouseOver');
  }

  onMouseClick(country: string, clickedPoint: any, origin: MapCountryComponent) {
  	console.debug(`Mouse Click: ${country}, ${JSON.stringify(clickedPoint.latLng)}`);
    origin.countryChange.emit(country);
  	origin.showModal();
  }

  @Input()
  public modal: ModalDirective;
 
  public showModal():void {
    this.modal.show();
  }
 
  public hideModal():void {
    this.modal.hide();
  }

}