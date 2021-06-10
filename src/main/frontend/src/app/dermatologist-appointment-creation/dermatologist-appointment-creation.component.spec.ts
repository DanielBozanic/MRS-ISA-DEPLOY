import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistAppointmentCreationComponent } from './dermatologist-appointment-creation.component';

describe('DermatologistAppointmentCreationComponent', () => {
  let component: DermatologistAppointmentCreationComponent;
  let fixture: ComponentFixture<DermatologistAppointmentCreationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologistAppointmentCreationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistAppointmentCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});