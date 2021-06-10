import { TestBed } from '@angular/core/testing';

import { PublicPharmacyProfileService } from './public-pharmacy-profile.service';

describe('PublicPharmacyProfileService', () => {
  let service: PublicPharmacyProfileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PublicPharmacyProfileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
