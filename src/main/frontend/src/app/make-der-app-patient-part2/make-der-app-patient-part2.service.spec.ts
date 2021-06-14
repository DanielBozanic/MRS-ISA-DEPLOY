import { TestBed } from '@angular/core/testing';

import { MakeDerAppPatientPart2Service } from './make-der-app-patient-part2.service';

describe('MakeDerAppPatientPart2Service', () => {
  let service: MakeDerAppPatientPart2Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MakeDerAppPatientPart2Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
