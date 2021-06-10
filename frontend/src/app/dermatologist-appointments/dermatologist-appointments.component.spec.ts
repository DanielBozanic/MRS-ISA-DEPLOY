import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistAppointmentsComponent } from './dermatologist-appointments.component';

describe('DermatologistAppointmentsComponent', () => {
  let component:DermatologistAppointmentsComponent;
  let fixture: ComponentFixture<DermatologistAppointmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologistAppointmentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistAppointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});