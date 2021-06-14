import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/login/auth.service';
import { Absence } from 'src/app/models/absence';
import { DummyModel } from 'src/app/models/dummy-model';
import { PharmacyAdmin } from 'src/app/models/pharmacy-admin';
import { PurchaseOrder } from 'src/app/models/purchase-order';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';
import { DisplayActivePurchaseOrdersComponent } from '../display-active-purchase-orders/display-active-purchase-orders.component';
import { Location } from '@angular/common';
import { DeclineAbsenceRequestComponent } from '../decline-absence-request/decline-absence-request.component';

@Component({
  selector: 'app-absence-requests',
  templateUrl: './absence-requests.component.html',
  styleUrls: ['./absence-requests.component.css'],
})
export class AbsenceRequestsComponent implements OnInit {
  displayedColumnsAbsenceRequestList: string[] = [
    'description',
    'start',
    'end',
    'doctorId',
    'approve',
    'decline',
  ];
  absenceList!: Absence[];
  pharmacyAdmin!: PharmacyAdmin;

  absenceListSource = new MatTableDataSource<any>(this.absenceList);

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
    this.pharmacyAdminService.getPharmacyAdminData().subscribe((data) => {
      this.pharmacyAdmin = data;
      this.pharmacyAdminService
        .getAbsenceRequests(this.pharmacyAdmin.pharmacy.id)
        .subscribe((res) => {
          this.absenceList = res;
          this.absenceListSource = new MatTableDataSource<any>(
            this.absenceList
          );
        });
    });
  }

  openDialog(absence: Absence): void {
    console.log(absence);
    //purchaseOrderMedicine.medicine = medicine;
    const dialogRef = this.dialog.open(DeclineAbsenceRequestComponent, {
      width: '450px',
      data: {
        message: '',
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      absence.description = result;
      if (result != null) {
        const index: number = this.absenceList.indexOf(absence);
        if (index !== -1) {
          this.pharmacyAdminService
            .declineAbsenceRequest(absence)
            .subscribe((res) => {
              this.pharmacyAdminService
                .getAbsenceRequests(this.pharmacyAdmin.pharmacy.id)
                .subscribe((res) => (this.absenceList = res));
            });
        }
      }
    });
  }

  approve(absence: Absence): void {
    console.log(absence);
    this.pharmacyAdminService
      .approveAbsenceRequest(absence)
      .subscribe((res) => {
        this.pharmacyAdminService
          .getAbsenceRequests(this.pharmacyAdmin.pharmacy.id)
          .subscribe((res) => (this.absenceList = res));
      });
  }

  back(): void {
    this.location.back();
  }

  logout() {
    this.authService.logout();
  }
}
