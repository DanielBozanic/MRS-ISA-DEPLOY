import { TestBed } from '@angular/core/testing';

import { PatientSchedFarmaApp3Service } from './patient-sched-farma-app3.service';

describe('PatientSchedFarmaApp3Service', () => {
  let service: PatientSchedFarmaApp3Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientSchedFarmaApp3Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
