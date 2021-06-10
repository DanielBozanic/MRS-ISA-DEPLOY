import { TestBed } from '@angular/core/testing';

import { PatientSubscribedPharmaciesService } from './patient-subscribed-pharmacies.service';

describe('PatientSubscribedPharmaciesService', () => {
  let service: PatientSubscribedPharmaciesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientSubscribedPharmaciesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
