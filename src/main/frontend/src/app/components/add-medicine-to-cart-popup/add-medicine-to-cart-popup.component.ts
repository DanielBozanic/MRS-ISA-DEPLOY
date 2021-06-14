import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
export interface DialogData {
  medicineName: String;
  quantity: Number;
  purchaseOrderName: string;
  purchaseOrderDate: Date;
}
@Component({
  selector: 'app-add-medicine-to-cart-popup',
  templateUrl: './add-medicine-to-cart-popup.component.html',
  styleUrls: ['./add-medicine-to-cart-popup.component.css'],
})
export class AddMedicineToCartPopupComponent {
  constructor(
    public dialogRef: MatDialogRef<AddMedicineToCartPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
