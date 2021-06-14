import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
export interface DialogData {
  startingDate: Date;
  endingDate: Date;
  description: string;
}
@Component({
  selector: 'app-action-promotion-popup',
  templateUrl: './action-promotion-popup.component.html',
  styleUrls: ['./action-promotion-popup.component.css'],
})
export class ActionPromotionPopupComponent {
  constructor(
    private formBuilder: FormBuilder,

    public dialogRef: MatDialogRef<ActionPromotionPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}
  actionPromotionForm = this.formBuilder.group({
    startingDate: '',
    endingDate: '',
    description: '',
  });
  onNoClick(): void {
    this.dialogRef.close();
  }
}
