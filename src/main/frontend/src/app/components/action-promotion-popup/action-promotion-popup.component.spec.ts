import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActionPromotionPopupComponent } from './action-promotion-popup.component';

describe('ActionPromotionPopupComponent', () => {
  let component: ActionPromotionPopupComponent;
  let fixture: ComponentFixture<ActionPromotionPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActionPromotionPopupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ActionPromotionPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
