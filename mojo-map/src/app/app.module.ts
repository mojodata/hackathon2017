import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AgmCoreModule } from 'angular2-google-maps/core';
import { Ng2MapModule } from 'ng2-map';

import { TooltipModule, ModalModule, TypeaheadModule } from 'ng2-bootstrap';

import { Ng2SmartTableModule } from 'ng2-smart-table';


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
import { NewsFeedService } from './news-feed/news-feed.service';

import * as d3 from 'd3';
import * as nv from 'nvd3';
// declare module 'd3' { let exportAs: any; export = exportAs; }
// declare module 'nv' { let exportAs: any; export = exportAs; }

import { nvD3 } from 'ng2-nvd3';
import { ColorBarComponent } from './color-bar/color-bar.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchBarComponent,
    GmapComponent,
    MapCountryComponent,
    TooltipAccountComponent,
    NewsFeedComponent,
    HoldingsTableComponent,
    nvD3,
    DonutChartComponent,
    ColorBarComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    TypeaheadModule.forRoot(),
    Ng2MapModule,
    Ng2SmartTableModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBSEpfaA0EKpfElDPEDQOQGSDyhs_l8P2I'
    })
  ],
  providers: [AccountService, CoordinateService, MapColorService, NewsFeedService],
  bootstrap: [AppComponent]
})
export class AppModule { }
