import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistReviewComponent } from './dermatologist-review.component';

describe('DermatologistReviewComponent', () => {
  let component: DermatologistReviewComponent;
  let fixture: ComponentFixture<DermatologistReviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologistReviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
