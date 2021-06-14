import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit, Inject, ViewChild } from '@angular/core';
import {
  MatDialog,
  MatDialogRef,
  MAT_DIALOG_DATA,
} from '@angular/material/dialog';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { DummyModel } from 'src/app/models/dummy-model';
import { Medicine } from 'src/app/models/medicine';
import { PharmacyAdmin } from 'src/app/models/pharmacy-admin';
import { PurchaseOrder } from 'src/app/models/purchase-order';
import { PurchaseOrderMedicine } from 'src/app/models/purchase-order-medicine';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';
import { Pharmacist } from 'src/app/user-complaint/user-complaint.component';
import { AddMedicineToCartPopupComponent } from '../add-medicine-to-cart-popup/add-medicine-to-cart-popup.component';
import { stringify } from '@angular/compiler/src/util';
import { SubmitPurchaseOrderPopupComponent } from '../submit-purchase-order-popup/submit-purchase-order-popup.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { AuthService } from 'src/app/login/auth.service';
import { Location } from '@angular/common';
import {
  MatSnackBar,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';

export interface DialogData {
  quantity: Number;
}

@Component({
  selector: 'app-medicine-purchase-order',
  templateUrl: './medicine-purchase-order.component.html',
  styleUrls: ['./medicine-purchase-order.component.css'],
})
export class MedicinePurchaseOrderComponent implements OnInit {
  displayedColumnsMedicineList: string[] = [
    'name',
    'composition',
    'form',
    'manufacturer',
    'medicineCode',
    'medicineType',
    'additionalComments',
    'prescription',
    'id',
    'actions',
  ];
  quantity!: Number;
  medicineList!: Medicine[];
  medicineListSource = new MatTableDataSource<any>(this.medicineList);
  purchaseOrderMedicineForm: FormGroup;
  pharmacy!: Pharmacist;
  pharmacyAdmin!: PharmacyAdmin;
  data!: DummyModel;
  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;
  verticalPosition: MatSnackBarVerticalPosition = 'top';
  purchaseOrderMedicine: PurchaseOrderMedicine = {
    quantity: 0,
    medicine: null as any,
  };
  purchaseOrder: PurchaseOrder = {
    id: null as any,
    pharmacy: null as any,
    purchaseOrderName: null as any,
    purchaseOrderDate: '' as any,
    dueDateOffer: '' as any,
    purchaseOrderMedicine: [],
    purchaseOrderSupplier: null as any,
  };
  isValid = false;
  constructor(
    public dialog: MatDialog,
    private pharmacyAdminService: PharmacyAdminService,
    private router: Router,
    private authService: AuthService,
    private location: Location,
    private _snackBar: MatSnackBar,

    private formBuilder: FormBuilder
  ) {
    this.purchaseOrderMedicineForm = this.formBuilder.group({
      purchaseOrderName: [],
      purchaseOrderDate: [],
    });
  }
  @ViewChild(MatPaginator)
  paginatorMedicine!: MatPaginator;

  ngOnInit(): void {
    this.purchaseOrderMedicineForm = this.formBuilder.group({
      purchaseOrderName: [],
      purchaseOrderDate: [],
    });
    this.pharmacyAdminService.getPharmacyAdminData().subscribe((data) => {
      this.pharmacyAdmin = data;
      this.pharmacyAdminService
        .getMedicineFromPharmacy(this.pharmacyAdmin.pharmacy)
        .subscribe((res) => {
          this.medicineList = res;
          this.medicineListSource = new MatTableDataSource<any>(
            this.medicineList
          );
          this.medicineListSource.paginator = this.paginatorMedicine;
        });
    });
  }

  openDialog(medicine: Medicine): void {
    let purchaseOrderMedicine: PurchaseOrderMedicine = {
      quantity: 0,
      medicine: null as any,
    };
    purchaseOrderMedicine.medicine = medicine;
    const dialogRef = this.dialog.open(AddMedicineToCartPopupComponent, {
      width: '250px',
      data: {
        medicineName: medicine.name,
        quantity: this.quantity,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
      purchaseOrderMedicine.quantity = result;
      if (result != null) {
        this.purchaseOrder.purchaseOrderMedicine.push(purchaseOrderMedicine);
      }
      this.isValid = true;
    });
  }
  createPurchaseOrder() {
    this.purchaseOrder.pharmacy = this.pharmacyAdmin.pharmacy;

    const dialogRef = this.dialog.open(SubmitPurchaseOrderPopupComponent, {
      width: '300px',
      data: {
        purchaseOrderName: '',
        purchaseOrderDate: '',
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
      this.purchaseOrder.purchaseOrderName = result.value.purchaseOrderName;
      this.purchaseOrder.purchaseOrderDate = result.value.purchaseOrderDate;
      if (result != null) {
        this.pharmacyAdminService
          .createPurchaseOrder(this.purchaseOrder)
          .subscribe((res) => {
            localStorage.removeItem('pharmacist');
            this.router.navigate(['/pharmacyProfilePage']);
            this.openSnackBar(
              'Successfully created a purchase order!',
              this.RESPONSE_OK
            );
          });
      }
    });
  }
  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, 'x', {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? 'back-green' : 'back-red',
    });
  }
  back(): void {
    this.location.back();
  }

  logout() {
    this.authService.logout();
  }
}
