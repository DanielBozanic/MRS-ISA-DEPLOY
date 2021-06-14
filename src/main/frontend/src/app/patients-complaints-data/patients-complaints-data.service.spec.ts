import { TestBed } from '@angular/core/testing';

import { PatientsComplaintsDataService } from './patients-complaints-data.service';

describe('PatientsComplaintsDataService', () => {
  let service: PatientsComplaintsDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientsComplaintsDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
