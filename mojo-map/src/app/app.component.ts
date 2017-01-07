import { Component, ViewContainerRef, OnChanges, SimpleChanges } from '@angular/core';
import { Ng2MapComponent } from 'ng2-map';
import Account from './dto/account.dto';
import Country from './dto/country.dto';
// import './rxjs-operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
	private viewContainerRef: ViewContainerRef;

	account: Account;
  countries: Array<Country>;

	public constructor(viewContainerRef: ViewContainerRef) {
		this.viewContainerRef = viewContainerRef;
		Ng2MapComponent['apiUrl'] = 'https://map.google.com/maps/api/js?key=AIzaSyBSEpfaA0EKpfElDPEDQOQGSDyhs_l8P2I';
	}

  title = 'Mojo UI!';

  onAccountUpdated(account: Account) {
  	this.account = account;
  }

  onCountriesUpdated(countries: Array<Country>) {
    this.countries = countries;
  }
}
