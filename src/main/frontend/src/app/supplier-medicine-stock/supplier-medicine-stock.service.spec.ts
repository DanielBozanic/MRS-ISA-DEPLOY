import { TestBed } from '@angular/core/testing';

import { SupplierMedicineStockService } from './supplier-medicine-stock.service';

describe('SupplierMedicineStockService', () => {
  let service: SupplierMedicineStockService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SupplierMedicineStockService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
