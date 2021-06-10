import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacistAppointmentsComponent } from './pharmacist-appointments.component';

describe('PharmacistAppointmentsComponent', () => {
  let component: PharmacistAppointmentsComponent;
  let fixture: ComponentFixture<PharmacistAppointmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistAppointmentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistAppointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});