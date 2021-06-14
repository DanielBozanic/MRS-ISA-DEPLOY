import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacyReviewComponent } from './pharmacy-review.component';

describe('PharmacyReviewComponent', () => {
  let component: PharmacyReviewComponent;
  let fixture: ComponentFixture<PharmacyReviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacyReviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacyReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
