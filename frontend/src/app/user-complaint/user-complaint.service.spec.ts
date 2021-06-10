import { TestBed } from '@angular/core/testing';

import { UserComplaintService } from './user-complaint.service';

describe('UserComplaintService', () => {
  let service: UserComplaintService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserComplaintService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
