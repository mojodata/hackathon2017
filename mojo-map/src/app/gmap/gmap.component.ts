import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { Output, EventEmitter } from '@angular/core';
import { OnChanges, SimpleChanges } from '@angular/core';
import { LatLng, LatLngLiteral, LatLngBoundsLiteral, LatLngBounds } from 'angular2-google-maps/core';
import { Ng2MapComponent } from "ng2-map";
import { SebmGoogleMap, SebmGoogleMapPolygon, PolygonManager } from 'angular2-google-maps/core';
import { GoogleMapsAPIWrapper } from 'angular2-google-maps/core';
import Account from '../dto/account.dto';
import Country from '../dto/country.dto';
import { AccountService } from '../account.service';
import { Observable } from 'rxjs/Observable';
import { CoordinateService } from '../coordinate.service';

@Component({
  selector: 'app-gmap',
  templateUrl: './gmap.component.html',
  styleUrls: ['./gmap.component.css'],
  providers: [ GoogleMapsAPIWrapper, PolygonManager ]
})
export class GmapComponent implements OnInit, OnChanges {

  @Input()
  account: Account;

  accountDetail: any;
  anyErrors: boolean;
  finished: boolean;

  countries: Array<Country>;

/*
  bounds: LatLngBoundsLiteral = {
    east: 180,
    west: -180,
    north: 90,
    south: -90
  }
  */

  @Output()
  countriesEmitter = new EventEmitter<Array<Country>>();

  targetCountryName: string;
  targetCountryCode: string;

  holding: any;

  constructor(private accountService: AccountService,
              private coordinateService: CoordinateService, private polygonManager: PolygonManager) {
  }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['account'].currentValue) {
      let subscription = this.accountService.getAccountDetails(changes['account'].currentValue).subscribe(
        (anAccountDetail) => {
          this.accountDetail = anAccountDetail;
          this.countries = this.getAllCountries(anAccountDetail);
        },
        error => this.anyErrors = true,
        () => this.finished = true
      );
    }
  }

  getAllCountries(anAccountDetail: any): Array<Country> {
    let keys = Object.keys(anAccountDetail.countryTotalMarketValue);

    let countries = [];

    keys.filter(aKey => aKey.length === 2).forEach(aKey => {
      let aCountry = anAccountDetail.countryTotalMarketValue[aKey];
      countries.push(new Country(aKey, aCountry.totalMarketValue, aCountry.rank));
    });

    this.countriesEmitter.emit(countries);

    return countries;
  }

  onCountryChange(countryCode) {
    this.targetCountryCode = countryCode;
    this.targetCountryName = this.coordinateService.getCountryName(countryCode);
    let subscription = this.accountService.getHoldings(this.account, countryCode).subscribe(
      (aHolding) => {
        // console.log(`${JSON.stringify(aHolding)}`);
        this.holding = aHolding;
      },
      error => this.anyErrors = true,
      () => this.finished = true
    );
  }
}
