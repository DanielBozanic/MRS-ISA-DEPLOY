import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllergiesOfPatientComponent } from './allergies-of-patient.component';

describe('AllergiesOfPatientComponent', () => {
  let component: AllergiesOfPatientComponent;
  let fixture: ComponentFixture<AllergiesOfPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllergiesOfPatientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllergiesOfPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
