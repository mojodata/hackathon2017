import { LatLngLiteral } from 'angular2-google-maps/core';

export default class Vertex implements LatLngLiteral {
	lat: number;
	lng: number;

	constructor(lat: number, lng: number) {
		this.lat = lat;
		this.lng = lng;
	};
}