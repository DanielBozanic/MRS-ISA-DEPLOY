import { TestBed } from '@angular/core/testing';

import { MedicineReviewService } from './medicine-review.service';

describe('MedicineReviewService', () => {
  let service: MedicineReviewService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicineReviewService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
