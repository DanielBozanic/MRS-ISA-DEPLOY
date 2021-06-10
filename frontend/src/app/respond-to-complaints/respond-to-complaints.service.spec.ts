import { TestBed } from '@angular/core/testing';

import { RespondToComplaintsService } from './respond-to-complaints.service';

describe('RespondToComplaintsService', () => {
  let service: RespondToComplaintsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RespondToComplaintsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
