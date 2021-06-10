import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistPatientComponent } from './dermatologist-patient.component';

describe('DermatologistPatientComponent', () => {
  let component: DermatologistPatientComponent;
  let fixture: ComponentFixture<DermatologistPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologistPatientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});