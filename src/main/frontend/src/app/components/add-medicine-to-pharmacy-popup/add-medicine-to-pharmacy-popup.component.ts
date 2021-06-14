import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
export interface DialogData {
  cost: '';
  amount: '';
}
@Component({
  selector: 'app-add-medicine-to-pharmacy-popup',
  templateUrl: './add-medicine-to-pharmacy-popup.component.html',
  styleUrls: ['./add-medicine-to-pharmacy-popup.component.css'],
})
export class AddMedicineToPharmacyPopupComponent {
  constructor(
    private formBuilder: FormBuilder,

    public dialogRef: MatDialogRef<AddMedicineToPharmacyPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}
  actionPromotionForm = this.formBuilder.group({
    cost: '',
    amount: '',
  });
  onNoClick(): void {
    this.dialogRef.close();
  }
}
