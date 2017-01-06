import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { OnChanges, SimpleChanges } from '@angular/core';
import { LatLng, LatLngLiteral } from 'angular2-google-maps/core';
import { Ng2MapComponent } from "ng2-map";
import { SebmGoogleMap, SebmGoogleMapPolygon, PolygonManager } from 'angular2-google-maps/core';
import { GoogleMapsAPIWrapper } from 'angular2-google-maps/core';
import Account from '../dto/account.dto';
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

  countries: Array<string>;

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

  getAllCountries(anAccountDetail: any): Array<string> {
    return Object.keys(anAccountDetail.countryTotalMarketValue).filter((key) => key !== "unknown");
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
