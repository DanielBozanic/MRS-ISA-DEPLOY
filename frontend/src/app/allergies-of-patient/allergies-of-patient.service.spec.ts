import { TestBed } from '@angular/core/testing';

import { AllergiesOfPatientService } from './allergies-of-patient.service';

describe('AllergiesOfPatientService', () => {
  let service: AllergiesOfPatientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AllergiesOfPatientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
