import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPharmacistToPharmacyPopupComponent } from './add-pharmacist-to-pharmacy-popup.component';

describe('AddPharmacistToPharmacyPopupComponent', () => {
  let component: AddPharmacistToPharmacyPopupComponent;
  let fixture: ComponentFixture<AddPharmacistToPharmacyPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddPharmacistToPharmacyPopupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPharmacistToPharmacyPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
