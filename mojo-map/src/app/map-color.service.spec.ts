/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { MapColorService } from './map-color.service';

describe('MapColorService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MapColorService]
    });
  });

  it('should ...', inject([MapColorService], (service: MapColorService) => {
    expect(service).toBeTruthy();
  }));
});
