import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDermatologistToPharmacyPopupComponent } from './add-dermatologist-to-pharmacy-popup.component';

describe('AddDermatologistToPharmacyPopupComponent', () => {
  let component: AddDermatologistToPharmacyPopupComponent;
  let fixture: ComponentFixture<AddDermatologistToPharmacyPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddDermatologistToPharmacyPopupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddDermatologistToPharmacyPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
