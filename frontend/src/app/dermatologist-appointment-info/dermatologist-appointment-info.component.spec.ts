import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistAppointmentInfoComponent } from './dermatologist-appointment-info.component';

describe('DermatologistAppointmentInfoComponent', () => {
  let component: DermatologistAppointmentInfoComponent;
  let fixture: ComponentFixture<DermatologistAppointmentInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologistAppointmentInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistAppointmentInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});