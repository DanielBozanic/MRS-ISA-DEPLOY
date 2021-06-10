import { TestBed } from '@angular/core/testing';

import { SearchFilterMedicineService } from './search-filter-medicine.service';

describe('SearchFilterMedicineService', () => {
  let service: SearchFilterMedicineService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchFilterMedicineService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
