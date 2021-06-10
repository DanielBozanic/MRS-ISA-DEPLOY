import { TestBed } from '@angular/core/testing';

import { SupplierUpdateService } from './supplier-update.service';

describe('SupplierUpdateService', () => {
  let service: SupplierUpdateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SupplierUpdateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
