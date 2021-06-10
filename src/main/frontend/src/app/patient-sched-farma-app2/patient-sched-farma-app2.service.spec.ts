import { TestBed } from '@angular/core/testing';

import { PatientSchedFarmaApp2Service } from './patient-sched-farma-app2.service';

describe('PatientSchedFarmaApp2Service', () => {
  let service: PatientSchedFarmaApp2Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientSchedFarmaApp2Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
