import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmitPurchaseOrderPopupComponent } from './submit-purchase-order-popup.component';

describe('SubmitPurchaseOrderPopupComponent', () => {
  let component: SubmitPurchaseOrderPopupComponent;
  let fixture: ComponentFixture<SubmitPurchaseOrderPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubmitPurchaseOrderPopupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubmitPurchaseOrderPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
