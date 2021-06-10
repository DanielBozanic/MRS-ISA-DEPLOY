import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientPastDermaAppComponent } from './patient-past-derma-app.component';

describe('PatientPastDermaAppComponent', () => {
  let component: PatientPastDermaAppComponent;
  let fixture: ComponentFixture<PatientPastDermaAppComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientPastDermaAppComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientPastDermaAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
