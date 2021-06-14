import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMedicineToCartPopupComponent } from './add-medicine-to-cart-popup.component';

describe('AddMedicineToCartPopupComponent', () => {
  let component: AddMedicineToCartPopupComponent;
  let fixture: ComponentFixture<AddMedicineToCartPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddMedicineToCartPopupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddMedicineToCartPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
