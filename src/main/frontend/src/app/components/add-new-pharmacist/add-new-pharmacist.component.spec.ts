import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewPharmacistComponent } from './add-new-pharmacist.component';

describe('AddNewPharmacistComponent', () => {
  let component: AddNewPharmacistComponent;
  let fixture: ComponentFixture<AddNewPharmacistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddNewPharmacistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNewPharmacistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
