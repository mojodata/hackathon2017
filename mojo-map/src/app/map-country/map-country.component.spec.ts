/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { MapCountryComponent } from './map-country.component';

describe('MapCountryComponent', () => {
  let component: MapCountryComponent;
  let fixture: ComponentFixture<MapCountryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MapCountryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MapCountryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
