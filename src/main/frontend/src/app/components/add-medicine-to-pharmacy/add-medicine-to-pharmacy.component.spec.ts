import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMedicineToPharmacyComponent } from './add-medicine-to-pharmacy.component';

describe('AddMedicineToPharmacyComponent', () => {
  let component: AddMedicineToPharmacyComponent;
  let fixture: ComponentFixture<AddMedicineToPharmacyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddMedicineToPharmacyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddMedicineToPharmacyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
