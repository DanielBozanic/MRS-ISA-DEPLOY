import { TestBed } from '@angular/core/testing';

import { PatientSchedFarmaAppService } from './patient-sched-farma-app.service';

describe('PatientSchedFarmaAppService', () => {
  let service: PatientSchedFarmaAppService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientSchedFarmaAppService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
