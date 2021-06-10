import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplierWriteOffersComponent } from './supplier-write-offers.component';

describe('SupplierWriteOffersComponent', () => {
  let component: SupplierWriteOffersComponent;
  let fixture: ComponentFixture<SupplierWriteOffersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SupplierWriteOffersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SupplierWriteOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
