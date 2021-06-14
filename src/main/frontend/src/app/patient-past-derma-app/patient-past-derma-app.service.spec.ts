import { TestBed } from '@angular/core/testing';

import { PatientPastDermaAppService } from './patient-past-derma-app.service';

describe('PatientPastDermaAppService', () => {
  let service: PatientPastDermaAppService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientPastDermaAppService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
