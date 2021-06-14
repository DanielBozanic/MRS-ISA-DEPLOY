import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateMedicineCommentPopupComponent } from './update-medicine-comment-popup.component';

describe('UpdateMedicineCommentPopupComponent', () => {
  let component: UpdateMedicineCommentPopupComponent;
  let fixture: ComponentFixture<UpdateMedicineCommentPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateMedicineCommentPopupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateMedicineCommentPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
