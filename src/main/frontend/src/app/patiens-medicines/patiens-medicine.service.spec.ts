import { TestBed } from '@angular/core/testing';

import { PatiensMedicineService } from './patiens-medicine.service';

describe('PatiensMedicineService', () => {
  let service: PatiensMedicineService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatiensMedicineService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
