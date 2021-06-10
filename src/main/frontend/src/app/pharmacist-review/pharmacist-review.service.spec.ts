import { TestBed } from '@angular/core/testing';

import { PharmacistReviewService } from './pharmacist-review.service';

describe('PharmacistReviewService', () => {
  let service: PharmacistReviewService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PharmacistReviewService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
