import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayActivePurchaseOrdersComponent } from './display-active-purchase-orders.component';

describe('DisplayActivePurchaseOrdersComponent', () => {
  let component: DisplayActivePurchaseOrdersComponent;
  let fixture: ComponentFixture<DisplayActivePurchaseOrdersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayActivePurchaseOrdersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayActivePurchaseOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
