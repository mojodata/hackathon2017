import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AgmCoreModule } from 'angular2-google-maps/core';
import { Ng2MapModule } from 'ng2-map';

import { TooltipModule, ModalModule } from 'ng2-bootstrap';

import { Ng2SmartTableModule } from 'ng2-smart-table';

import { D3Service } from 'd3-ng2-service'; // <-- import statement

import { AppComponent } from './app.component';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { GmapComponent } from './gmap/gmap.component';
import { MapCountryComponent } from './map-country/map-country.component';
import { TooltipAccountComponent } from './tooltip-account/tooltip-account.component';
import { NewsFeedComponent } from './news-feed/news-feed.component';
import { AccountService } from './account.service';
import { CoordinateService } from './coordinate.service';
import { MapColorService } from './map-color.service';
import { HoldingsTableComponent } from './holdings-table/holdings-table.component';
import { DonutChartComponent } from './donut-chart/donut-chart.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchBarComponent,
    GmapComponent,
    MapCountryComponent,
    TooltipAccountComponent,
    NewsFeedComponent,
    HoldingsTableComponent,
    DonutChartComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    Ng2MapModule,
    Ng2SmartTableModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBSEpfaA0EKpfElDPEDQOQGSDyhs_l8P2I'
    })
  ],
  providers: [AccountService, CoordinateService, MapColorService, D3Service],
  bootstrap: [AppComponent]
})
export class AppModule { }
