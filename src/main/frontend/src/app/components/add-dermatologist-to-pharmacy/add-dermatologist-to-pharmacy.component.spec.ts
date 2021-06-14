import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDermatologistToPharmacyComponent } from './add-dermatologist-to-pharmacy.component';

describe('AddDermatologistToPharmacyComponent', () => {
  let component: AddDermatologistToPharmacyComponent;
  let fixture: ComponentFixture<AddDermatologistToPharmacyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddDermatologistToPharmacyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddDermatologistToPharmacyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
