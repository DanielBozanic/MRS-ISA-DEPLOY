import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicineReviewComponent } from './medicine-review.component';

describe('MedicineReviewComponent', () => {
  let component: MedicineReviewComponent;
  let fixture: ComponentFixture<MedicineReviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicineReviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicineReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
