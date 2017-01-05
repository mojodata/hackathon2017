import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AgmCoreModule } from 'angular2-google-maps/core';

import { TooltipModule, ModalModule } from 'ng2-bootstrap';

import { AppComponent } from './app.component';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { GmapComponent } from './gmap/gmap.component';
import { MapCountryComponent } from './map-country/map-country.component';
import { TooltipAccountComponent } from './tooltip-account/tooltip-account.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchBarComponent,
    GmapComponent,
    MapCountryComponent,
    TooltipAccountComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBSEpfaA0EKpfElDPEDQOQGSDyhs_l8P2I'
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
