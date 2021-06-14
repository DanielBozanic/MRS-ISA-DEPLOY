import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacistPatientComponent } from './pharmacist-patient.component';

describe('PharmacistPatientComponent', () => {
  let component: PharmacistPatientComponent;
  let fixture: ComponentFixture<PharmacistPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistPatientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});