import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientSubscribedPharmaciesComponent } from './patient-subscribed-pharmacies.component';

describe('PatientSubscribedPharmaciesComponent', () => {
  let component: PatientSubscribedPharmaciesComponent;
  let fixture: ComponentFixture<PatientSubscribedPharmaciesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientSubscribedPharmaciesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientSubscribedPharmaciesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
