import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakeDerAppPatientPart2Component } from './make-der-app-patient-part2.component';

describe('MakeDerAppPatientPart2Component', () => {
  let component: MakeDerAppPatientPart2Component;
  let fixture: ComponentFixture<MakeDerAppPatientPart2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MakeDerAppPatientPart2Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MakeDerAppPatientPart2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
