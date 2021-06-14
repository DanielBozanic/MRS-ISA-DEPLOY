import { TestBed } from '@angular/core/testing';

import { PatientPastPharAppService } from './patient-past-phar-app.service';

describe('PatientPastPharAppService', () => {
  let service: PatientPastPharAppService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientPastPharAppService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
