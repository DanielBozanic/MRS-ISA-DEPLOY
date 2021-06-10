import { TestBed } from '@angular/core/testing';

import { SystemAdminUpdateService } from './system-admin-update.service';

describe('SystemAdminUpdateService', () => {
  let service: SystemAdminUpdateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SystemAdminUpdateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
