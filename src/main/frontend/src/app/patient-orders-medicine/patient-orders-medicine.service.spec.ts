import { TestBed } from '@angular/core/testing';

import { PatientOrdersMedicineService } from './patient-orders-medicine.service';

describe('PatientOrdersMedicineService', () => {
  let service: PatientOrdersMedicineService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientOrdersMedicineService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
