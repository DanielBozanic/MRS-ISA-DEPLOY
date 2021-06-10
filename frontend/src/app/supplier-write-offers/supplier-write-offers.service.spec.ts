import { TestBed } from '@angular/core/testing';

import { SupplierWriteOffersService } from './supplier-write-offers.service';

describe('SupplierWriteOffersService', () => {
  let service: SupplierWriteOffersService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SupplierWriteOffersService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
