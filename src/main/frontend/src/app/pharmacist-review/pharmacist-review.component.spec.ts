import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacistReviewComponent } from './pharmacist-review.component';

describe('PharmacistReviewComponent', () => {
  let component: PharmacistReviewComponent;
  let fixture: ComponentFixture<PharmacistReviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistReviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
