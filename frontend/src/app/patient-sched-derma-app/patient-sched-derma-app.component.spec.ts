import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientSchedDermaAppComponent } from './patient-sched-derma-app.component';

describe('PatientSchedDermaAppComponent', () => {
  let component: PatientSchedDermaAppComponent;
  let fixture: ComponentFixture<PatientSchedDermaAppComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientSchedDermaAppComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientSchedDermaAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
