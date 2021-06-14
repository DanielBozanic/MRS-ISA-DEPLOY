import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistAppointmentPopupComponent } from './dermatologist-appointment-popup.component';

describe('DermatologistAppointmentPopupComponent', () => {
  let component: DermatologistAppointmentPopupComponent;
  let fixture: ComponentFixture<DermatologistAppointmentPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologistAppointmentPopupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistAppointmentPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
