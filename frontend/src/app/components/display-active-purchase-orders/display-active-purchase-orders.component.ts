import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Medicine } from 'src/app/models/medicine';
import { PurchaseOrder } from 'src/app/models/purchase-order';
import { PurchaseOrderMedicine } from 'src/app/models/purchase-order-medicine';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';
export interface DialogData {
  purchaseOrderMedicine: PurchaseOrderMedicine[];
  medicine: Medicine;
  id: Number;
  purchaseOrder: PurchaseOrder;
}
@Component({
  selector: 'app-display-active-purchase-orders',
  templateUrl: './display-active-purchase-orders.component.html',
  styleUrls: ['./display-active-purchase-orders.component.css'],
})
export class DisplayActivePurchaseOrdersComponent implements OnInit {
  purchaseOrderMedicineList!: PurchaseOrderMedicine[];
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    private pharmacyAdminService: PharmacyAdminService
  ) {}

  ngOnInit(): void {
    this.pharmacyAdminService
      .getPurchaseOrder(this.data.purchaseOrder.id)
      .subscribe((res) => {
        this.purchaseOrderMedicineList = res;
      });
  }
}
