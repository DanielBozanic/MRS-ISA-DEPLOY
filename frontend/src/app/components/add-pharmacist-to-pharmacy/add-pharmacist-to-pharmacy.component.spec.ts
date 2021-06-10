import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPharmacistToPharmacyComponent } from './add-pharmacist-to-pharmacy.component';

describe('AddPharmacistToPharmacyComponent', () => {
  let component: AddPharmacistToPharmacyComponent;
  let fixture: ComponentFixture<AddPharmacistToPharmacyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddPharmacistToPharmacyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPharmacistToPharmacyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
