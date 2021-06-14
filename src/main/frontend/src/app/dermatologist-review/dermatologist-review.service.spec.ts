import { TestBed } from '@angular/core/testing';

import { DermatologistReviewService } from './dermatologist-review.service';

describe('DermatologistReviewService', () => {
  let service: DermatologistReviewService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DermatologistReviewService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
