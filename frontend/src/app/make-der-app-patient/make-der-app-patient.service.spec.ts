import { TestBed } from '@angular/core/testing';

import { MakeDerAppPatientService } from './make-der-app-patient.service';

describe('MakeDerAppPatientService', () => {
  let service: MakeDerAppPatientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MakeDerAppPatientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
