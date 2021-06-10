import { TestBed } from '@angular/core/testing';

import { SupplierViewOffersService } from './supplier-view-offers.service';

describe('SupplierViewOffersService', () => {
  let service: SupplierViewOffersService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SupplierViewOffersService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
