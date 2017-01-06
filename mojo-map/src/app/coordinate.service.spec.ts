/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { CoordinateService } from './coordinate.service';

describe('CoordinateService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CoordinateService]
    });
  });

  it('should ...', inject([CoordinateService], (service: CoordinateService) => {
    expect(service).toBeTruthy();
  }));
});
