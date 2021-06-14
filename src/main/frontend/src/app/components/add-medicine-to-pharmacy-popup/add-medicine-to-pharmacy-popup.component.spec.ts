import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMedicineToPharmacyPopupComponent } from './add-medicine-to-pharmacy-popup.component';

describe('AddMedicineToPharmacyPopupComponent', () => {
  let component: AddMedicineToPharmacyPopupComponent;
  let fixture: ComponentFixture<AddMedicineToPharmacyPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddMedicineToPharmacyPopupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddMedicineToPharmacyPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
