import { TestBed } from '@angular/core/testing';

import { PatientSchedDermaAppService } from './patient-sched-derma-app.service';

describe('PatientSchedDermaAppService', () => {
  let service: PatientSchedDermaAppService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientSchedDermaAppService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
