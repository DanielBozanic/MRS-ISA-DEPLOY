import { TestBed } from '@angular/core/testing';

import { ChangePatientDataService } from './change-patient-data.service';

describe('ChangePatientDataService', () => {
  let service: ChangePatientDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChangePatientDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
