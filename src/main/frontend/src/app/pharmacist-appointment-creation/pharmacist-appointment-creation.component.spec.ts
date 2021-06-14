import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacistAppointmentCreationComponent } from './pharmacist-appointment-creation.component';

describe('PharmacistAppointmentCreationComponent', () => {
  let component: PharmacistAppointmentCreationComponent;
  let fixture: ComponentFixture<PharmacistAppointmentCreationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistAppointmentCreationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistAppointmentCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});