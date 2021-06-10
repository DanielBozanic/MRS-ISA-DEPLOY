import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientPastPharAppComponent } from './patient-past-phar-app.component';

describe('PatientPastPharAppComponent', () => {
  let component: PatientPastPharAppComponent;
  let fixture: ComponentFixture<PatientPastPharAppComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientPastPharAppComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientPastPharAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
