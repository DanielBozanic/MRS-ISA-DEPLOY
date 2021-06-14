import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientSchedFarmaApp3Component } from './patient-sched-farma-app3.component';

describe('PatientSchedFarmaApp3Component', () => {
  let component: PatientSchedFarmaApp3Component;
  let fixture: ComponentFixture<PatientSchedFarmaApp3Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientSchedFarmaApp3Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientSchedFarmaApp3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
