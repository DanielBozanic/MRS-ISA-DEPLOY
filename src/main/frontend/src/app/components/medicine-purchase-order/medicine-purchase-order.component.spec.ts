import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicinePurchaseOrderComponent } from './medicine-purchase-order.component';

describe('MedicinePurchaseOrderComponent', () => {
  let component: MedicinePurchaseOrderComponent;
  let fixture: ComponentFixture<MedicinePurchaseOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicinePurchaseOrderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicinePurchaseOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
