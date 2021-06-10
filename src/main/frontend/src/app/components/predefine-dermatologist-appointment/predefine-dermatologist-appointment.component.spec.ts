import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredefineDermatologistAppointmentComponent } from './predefine-dermatologist-appointment.component';

describe('PredefineDermatologistAppointmentComponent', () => {
  let component: PredefineDermatologistAppointmentComponent;
  let fixture: ComponentFixture<PredefineDermatologistAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PredefineDermatologistAppointmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PredefineDermatologistAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
