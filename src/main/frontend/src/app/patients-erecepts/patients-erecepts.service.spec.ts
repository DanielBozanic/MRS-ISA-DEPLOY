import { TestBed } from '@angular/core/testing';

import { PatientsEreceptsService } from './patients-erecepts.service';

describe('PatientsEreceptsService', () => {
  let service: PatientsEreceptsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientsEreceptsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
