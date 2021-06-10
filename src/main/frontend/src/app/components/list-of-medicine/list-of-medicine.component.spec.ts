import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOfMedicineComponent } from './list-of-medicine.component';

describe('ListOfMedicineComponent', () => {
  let component: ListOfMedicineComponent;
  let fixture: ComponentFixture<ListOfMedicineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListOfMedicineComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListOfMedicineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
