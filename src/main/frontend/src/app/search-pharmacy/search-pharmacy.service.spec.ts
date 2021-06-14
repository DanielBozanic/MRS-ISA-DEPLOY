import { TestBed } from '@angular/core/testing';

import { SearchPharmacyService } from './search-pharmacy.service';

describe('SearchPharmacyService', () => {
  let service: SearchPharmacyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchPharmacyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
