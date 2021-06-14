import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
export interface DialogData {
  additionalComments: '';
}
@Component({
  selector: 'app-update-medicine-comment-popup',
  templateUrl: './update-medicine-comment-popup.component.html',
  styleUrls: ['./update-medicine-comment-popup.component.css'],
})
export class UpdateMedicineCommentPopupComponent {
  constructor(
    private formBuilder: FormBuilder,

    public dialogRef: MatDialogRef<UpdateMedicineCommentPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}
  actionPromotionForm = this.formBuilder.group({
    additionalComments: this.data.additionalComments,
  });
  onNoClick(): void {
    this.dialogRef.close();
  }
}
