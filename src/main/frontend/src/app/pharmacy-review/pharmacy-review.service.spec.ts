import { TestBed } from '@angular/core/testing';

import { PharmacyReviewService } from './pharmacy-review.service';

describe('PharmacyReviewService', () => {
  let service: PharmacyReviewService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PharmacyReviewService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
