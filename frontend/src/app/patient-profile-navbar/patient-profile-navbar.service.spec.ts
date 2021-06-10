import { TestBed } from '@angular/core/testing';

import { PatientProfileNavbarService } from './patient-profile-navbar.service';

describe('PatientProfileNavbarService', () => {
  let service: PatientProfileNavbarService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientProfileNavbarService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
