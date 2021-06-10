import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import {
  AddMedicineToCartPopupComponent,
  DialogData,
} from '../add-medicine-to-cart-popup/add-medicine-to-cart-popup.component';

@Component({
  selector: 'app-submit-purchase-order-popup',
  templateUrl: './submit-purchase-order-popup.component.html',
  styleUrls: ['./submit-purchase-order-popup.component.css'],
})
export class SubmitPurchaseOrderPopupComponent {
  constructor(
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<SubmitPurchaseOrderPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}
  purchaseOrderForm = this.formBuilder.group({
    purchaseOrderName: '',
    purchaseOrderDate: '',
  });
  onNoClick(): void {
    this.dialogRef.close();
  }
}
