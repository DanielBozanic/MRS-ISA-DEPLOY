import { Component, OnInit, ViewChild } from '@angular/core';
import { Pharmacy } from 'src/app/user-complaint/user-complaint.component';
import { Location } from '@angular/common';
import { AuthService } from 'src/app/login/auth.service';
import { PublicPharmacyProfileService } from './public-pharmacy-profile.service';
import {
  MatSnackBar,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { MedicinePharmacy } from 'src/app/models/medicine-pharmacy';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Appointment } from 'src/app/models/appointment';
import { Employment } from 'src/app/models/employment';
import { Pharmacist } from 'src/app/models/pharmacist';
import { Dermatologist } from 'src/app/models/dermatologist';

@Component({
  selector: 'app-public-pharmacy-profile-page',
  templateUrl: './public-pharmacy-profile-page.component.html',
  styleUrls: ['./public-pharmacy-profile-page.component.css'],
})
export class PublicPharmacyProfilePageComponent implements OnInit {
  displayedColumnsMedicinePharmacyList: string[] = [
    'medicine',
    'amount',
    'cost',
  ];
  displayedColumnsDermatolgoistAppointmentList: string[] = [
    'name',
    'surname',
    'price',
    'start',
  ];
  displayedColumnsDermatolgoistList: string[] = [
    'name',
    'surname',
    'email',
    'phoneNumber',
    'rating',
  ];
  displayedColumnsPharmacistList: string[] = [
    'name',
    'surname',
    'email',
    'phoneNumber',
    'rating',
  ];
  pharmacy!: Pharmacy;
  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;
  verticalPosition: MatSnackBarVerticalPosition = 'top';
  medicinePharmacyList!: MedicinePharmacy[];
  medicinePharmacyListSource = new MatTableDataSource<any>(
    this.medicinePharmacyList
  );
  dermatologistAppointmentList!: Appointment[];
  dermatologistAppointmentListSource = new MatTableDataSource<any>(
    this.dermatologistAppointmentList
  );
  employeeList!: Employment[];
  pharmacistList!: Pharmacist[];
  pharmacistListSource = new MatTableDataSource<any>(
    this.dermatologistAppointmentList
  );
  dermatologistList!: Dermatologist[];
  dermatologistListSource = new MatTableDataSource<any>(
    this.dermatologistAppointmentList
  );
  loggedOn = this.authService.getToken() != null ? true : false;

  constructor(
    private location: Location,
    private authService: AuthService,
    private publicPharmacyProfilePageService: PublicPharmacyProfileService,
    private _snackBar: MatSnackBar
  ) {}

  @ViewChild(MatPaginator)
  paginatorMedicinePharmacy!: MatPaginator;
  @ViewChild(MatPaginator)
  paginatorDermatologistAppointment!: MatPaginator;
  ngOnInit(): void {
    this.pharmacistList = [];
    this.dermatologistList = [];
    this.pharmacy = JSON.parse(localStorage.getItem('pharmacy') || '{}');
    this.publicPharmacyProfilePageService
      .getMedicinePharmacy(this.pharmacy.id)
      .subscribe((result) => {
        this.medicinePharmacyList = result;
        this.medicinePharmacyListSource = new MatTableDataSource<any>(
          this.medicinePharmacyList
        );
        this.medicinePharmacyListSource.paginator =
          this.paginatorMedicinePharmacy;
      });
    this.publicPharmacyProfilePageService
      .getDermatologistAppointments(this.pharmacy.id)
      .subscribe((result) => {
        this.dermatologistAppointmentList = result;
        this.dermatologistAppointmentListSource = new MatTableDataSource<any>(
          this.dermatologistAppointmentList
        );
        this.dermatologistAppointmentListSource.paginator =
          this.paginatorDermatologistAppointment;
      });
    this.publicPharmacyProfilePageService
      .getPharmacyEmployees(this.pharmacy.id)
      .subscribe((result) => {
        this.employeeList = result;
        this.employeeList.forEach((employee) => {
          if (employee.dermatologist) {
            this.dermatologistList.push(employee.dermatologist);
          } else this.pharmacistList.push(employee.pharmacist);
        });
        this.dermatologistListSource = new MatTableDataSource<any>(
          this.dermatologistList
        );
        this.pharmacistListSource = new MatTableDataSource<any>(
          this.pharmacistList
        );
      });
  }

  subscribeToPromotions() {
    this.publicPharmacyProfilePageService
      .subscribeToPromotions(this.pharmacy)
      .subscribe((response) => {
        this.openSnackBar(response, this.RESPONSE_OK);
      });
  }

  back(): void {
    localStorage.removeItem('pharmacy');
    this.location.back();
  }

  logout() {
    this.authService.logout();
  }

  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, 'x', {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? 'back-green' : 'back-red',
    });
  }
}
