import { TestBed } from '@angular/core/testing';

import { RegisterPharmaciesService } from './register-pharmacies.service';

describe('RegisterPharmaciesService', () => {
  let service: RegisterPharmaciesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegisterPharmaciesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
