import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientOrdersMedicineComponent } from './patient-orders-medicine.component';

describe('PatientOrdersMedicineComponent', () => {
  let component: PatientOrdersMedicineComponent;
  let fixture: ComponentFixture<PatientOrdersMedicineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientOrdersMedicineComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientOrdersMedicineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
