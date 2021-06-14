import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { DummyModel } from 'src/app/models/dummy-model';
import { Medicine } from 'src/app/models/medicine';
import { PurchaseOrder } from 'src/app/models/purchase-order';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';
import { DisplayActivePurchaseOrdersComponent } from '../display-active-purchase-orders/display-active-purchase-orders.component';
import { Location } from '@angular/common';
import { AuthService } from 'src/app/login/auth.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-list-of-purchase-orders',
  templateUrl: './list-of-purchase-orders.component.html',
  styleUrls: ['./list-of-purchase-orders.component.css'],
})
export class ListOfPurchaseOrdersComponent implements OnInit {
  displayedColumnsPurchaseOrderList: string[] = [
    'dueDateOffer',
    'purchaseOrderName',
    'actions',
  ];
  purchaseOrderList!: PurchaseOrder[];
  purchaseOrderListListSource = new MatTableDataSource<any>(
    this.purchaseOrderList
  );

  purchaseOrderId!: DummyModel;
  constructor(
    private pharmacyAdminService: PharmacyAdminService,
    private router: Router,
    private formBuilder: FormBuilder,
    private authService: AuthService,
    public dialog: MatDialog,
    private location: Location
  ) {}
  @ViewChild(MatPaginator)
  paginatorPurchaseOrder!: MatPaginator;

  ngOnInit(): void {
    this.pharmacyAdminService.getActivePurchaseOrders().subscribe((res) => {
      this.purchaseOrderList = res;
      this.purchaseOrderListListSource = new MatTableDataSource<any>(
        this.purchaseOrderList
      );
      this.purchaseOrderListListSource.paginator = this.paginatorPurchaseOrder;
    });
  }

  displayPurchaseOrderData(purchaseOrder: PurchaseOrder) {
    const dialogRef = this.dialog.open(DisplayActivePurchaseOrdersComponent, {
      data: {
        purchaseOrder: purchaseOrder,
      },
    });

    dialogRef.afterClosed().subscribe();
  }

  back(): void {
    this.location.back();
  }

  logout() {
    this.authService.logout();
  }
}
