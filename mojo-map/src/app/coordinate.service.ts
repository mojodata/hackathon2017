import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { LatLngLiteral, PolygonManager, SebmGoogleMapPolygon, PolyMouseEvent } from 'angular2-google-maps/core';

import { MapCountryComponent } from './map-country/map-country.component';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class CoordinateService {

  constructor(private http: Http) {}

  addRegion(
              polygonManager: PolygonManager,
              aRegion: Array<LatLngLiteral>,
              fillColor: string,
              country: string,
              onMouseClick: Function,
              origin: MapCountryComponent
              ): SebmGoogleMapPolygon {
   let aPolygon = new SebmGoogleMapPolygon(polygonManager);
    aPolygon.paths = aRegion;
    aPolygon.fillColor = fillColor;

    /*
    aPolygon.polyClick.subscribe(
      (polyMouseEvent: PolyMouseEvent) => {
        console.log(`clicked.`)
      },
      error => console.error(error),
      () => console.log(`Finished`)
    );
    */
    // aPolygon.polyClick.subscribe(onMouseClick);

    polygonManager.addPolygon(aPolygon);

    polygonManager.createEventObservable("click", aPolygon).subscribe(
      (object: any) => { 
        onMouseClick(country, object, origin);
      },
      // (object) => { console.log(`Country = ${country}, ${JSON.stringify(object)}`)},
      error => console.error(error),
      () => console.log(`Finished`)
    );

    return aPolygon;
  }

  removeRegion(polygonManager: PolygonManager, polygon: SebmGoogleMapPolygon) {
    polygonManager.deletePolygon(polygon);
  }

  getRegions(country: string): Observable<Array<Array<LatLngLiteral>>> {
    return this.http.get(`http://position-dashboard-service.mybluemix.net/api/coordinates/${country}`)
      .map(this.extractRegions)
      .catch(this.handleRegionsError);
  }

  private extractRegions(res: Response) {
    let body = res.json();

    return body || {};
  }

  private handleRegionsError(error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} = ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }

}
