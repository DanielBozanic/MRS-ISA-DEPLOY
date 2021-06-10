import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplierMedicineStockComponent } from './supplier-medicine-stock.component';

describe('SupplierMedicineStockComponent', () => {
  let component: SupplierMedicineStockComponent;
  let fixture: ComponentFixture<SupplierMedicineStockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SupplierMedicineStockComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SupplierMedicineStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
