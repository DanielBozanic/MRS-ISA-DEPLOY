import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacistAbsenceComponent } from './pharmacist-absence.component';

describe('PharmacistAbsenceComponent', () => {
  let component: PharmacistAbsenceComponent;
  let fixture: ComponentFixture<PharmacistAbsenceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistAbsenceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistAbsenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});