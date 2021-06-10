import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplierViewOffersComponent } from './supplier-view-offers.component';

describe('SupplierViewOffersComponent', () => {
  let component: SupplierViewOffersComponent;
  let fixture: ComponentFixture<SupplierViewOffersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SupplierViewOffersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SupplierViewOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
