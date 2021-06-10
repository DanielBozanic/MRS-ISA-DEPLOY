import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchFilterMedicineComponent } from './search-filter-medicine.component';

describe('SearchFilterMedicineComponent', () => {
  let component: SearchFilterMedicineComponent;
  let fixture: ComponentFixture<SearchFilterMedicineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchFilterMedicineComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchFilterMedicineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
