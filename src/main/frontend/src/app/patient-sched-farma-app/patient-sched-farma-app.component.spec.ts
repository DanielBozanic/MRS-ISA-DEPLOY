import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientSchedFarmaAppComponent } from './patient-sched-farma-app.component';

describe('PatientSchedFarmaAppComponent', () => {
  let component: PatientSchedFarmaAppComponent;
  let fixture: ComponentFixture<PatientSchedFarmaAppComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientSchedFarmaAppComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientSchedFarmaAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
