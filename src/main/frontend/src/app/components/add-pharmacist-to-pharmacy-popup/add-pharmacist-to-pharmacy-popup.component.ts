import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
export interface DialogData {
  start: Date;
  end: Date;
}
@Component({
  selector: 'app-add-pharmacist-to-pharmacy-popup',
  templateUrl: './add-pharmacist-to-pharmacy-popup.component.html',
  styleUrls: ['./add-pharmacist-to-pharmacy-popup.component.css'],
})
export class AddPharmacistToPharmacyPopupComponent {
  constructor(
    private formBuilder: FormBuilder,

    public dialogRef: MatDialogRef<AddPharmacistToPharmacyPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}
  employmentForm = this.formBuilder.group({
    start: '',
    end: '',
  });
  onNoClick(): void {
    this.dialogRef.close();
  }
}
