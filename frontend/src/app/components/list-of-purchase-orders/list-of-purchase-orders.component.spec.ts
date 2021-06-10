import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOfPurchaseOrdersComponent } from './list-of-purchase-orders.component';

describe('ListOfPurchaseOrdersComponent', () => {
  let component: ListOfPurchaseOrdersComponent;
  let fixture: ComponentFixture<ListOfPurchaseOrdersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListOfPurchaseOrdersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListOfPurchaseOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
