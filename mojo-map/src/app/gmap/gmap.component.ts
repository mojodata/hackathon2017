import { Component, OnInit } from '@angular/core';
import { LatLng, LatLngLiteral } from 'angular2-google-maps/core';
import { SebmGoogleMap, SebmGoogleMapPolygon } from 'angular2-google-maps/core';
import { GoogleMapsAPIWrapper } from 'angular2-google-maps/core';
import Vertex from '../dto/vertex.dto';

@Component({
  selector: 'app-gmap',
  templateUrl: './gmap.component.html',
  styleUrls: ['./gmap.component.css'],
  providers: [ GoogleMapsAPIWrapper ]
})
export class GmapComponent implements OnInit {

	lat: number = 51.678418;
  lng: number = 7.809007;

  kmlUrl = "http://localhost:3000/countries_world.kml";
  googleMapsApi: GoogleMapsAPIWrapper;

  constructor(googleMapsApi: GoogleMapsAPIWrapper) {
    this.googleMapsApi = googleMapsApi;
    this.googleMapsApi = googleMapsApi;
  }

  ngOnInit() {
  	
  }
}


