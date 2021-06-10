import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacistAppointmentInfoComponent } from './pharmacist-appointment-info.component';

describe('PharmacistAppointmentInfoComponent', () => {
  let component: PharmacistAppointmentInfoComponent;
  let fixture: ComponentFixture<PharmacistAppointmentInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistAppointmentInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistAppointmentInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});