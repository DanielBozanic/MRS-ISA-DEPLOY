import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistAbsenceComponent } from './dermatologist-absence.component';

describe('DermatologistAbsenceComponent', () => {
  let component: DermatologistAbsenceComponent;
  let fixture: ComponentFixture<DermatologistAbsenceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologistAbsenceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistAbsenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});