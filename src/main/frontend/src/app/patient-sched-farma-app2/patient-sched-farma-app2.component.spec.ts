import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientSchedFarmaApp2Component } from './patient-sched-farma-app2.component';

describe('PatientSchedFarmaApp2Component', () => {
  let component: PatientSchedFarmaApp2Component;
  let fixture: ComponentFixture<PatientSchedFarmaApp2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientSchedFarmaApp2Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientSchedFarmaApp2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
