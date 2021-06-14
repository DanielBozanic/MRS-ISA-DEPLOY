import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Appointment } from 'src/app/models/appointment';
import { Dermatologist } from 'src/app/models/dermatologist';
import { AppointmentDermatologist } from 'src/app/models/appointmentDermatologist';
import { PharmacyAdmin } from 'src/app/models/pharmacy-admin';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';
import { DermatologistAppointmentPopupComponent } from '../dermatologist-appointment-popup/dermatologist-appointment-popup.component';
import { Location } from '@angular/common';
import { AuthService } from 'src/app/login/auth.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import {
  MatSnackBar,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';

@Component({
  selector: 'app-predefine-dermatologist-appointment',
  templateUrl: './predefine-dermatologist-appointment.component.html',
  styleUrls: ['./predefine-dermatologist-appointment.component.css'],
})
export class PredefineDermatologistAppointmentComponent implements OnInit {
  displayedColumnsDermatologistList: string[] = ['name', 'surname', 'actions'];
  pharmacyAdmin!: PharmacyAdmin;
  dermatologist!: Dermatologist;
  dermatologistList!: Dermatologist[];
  dermatologistListSource = new MatTableDataSource<any>(this.dermatologistList);
  appointmentForm: FormGroup;
  isValid = false;

  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;
  verticalPosition: MatSnackBarVerticalPosition = 'top';
  appointment: Appointment = {
    start: '' as any,
    end: '' as any,
    price: null as any,
    discount: null as any,
    done: null as any,
    pharmacy: null as any,
    dermatologist: null as any,
  };
  constructor(
    public dialog: MatDialog,
    private pharmacyAdminService: PharmacyAdminService,
    private location: Location,
    private authService: AuthService,
    private _snackBar: MatSnackBar,
    private formBuilder: FormBuilder
  ) {
    this.appointmentForm = this.formBuilder.group({
      price: [],
      duration: [],
    });
  }
  @ViewChild(MatPaginator)
  paginatorPurchaseOrder!: MatPaginator;

  ngOnInit(): void {
    this.appointmentForm = this.formBuilder.group({
      price: [],
      duration: [],
    });
    this.pharmacyAdminService.getPharmacyAdminData().subscribe((data) => {
      this.pharmacyAdmin = data;
    });

    this.pharmacyAdminService.getAllDermatologists().subscribe((data) => {
      this.dermatologistList = data;
      this.dermatologistListSource = new MatTableDataSource<any>(
        this.dermatologistList
      );
    });
  }

  openDialog(): void {
    //    let dermatologistAppointment: DermatologistAppointment = {
    //      appointment: null as any,
    //      dermatologist: null as any,
    //    };
    this.appointment.pharmacy = this.pharmacyAdmin.pharmacy;

    const dialogRef = this.dialog.open(DermatologistAppointmentPopupComponent, {
      data: {
        date: '',
        time: '',
        duration: '',
        price: '',
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.appointment.start = result.value.date;
      let copy = new Date(+this.appointment.start);
      let time = result.value.time.split(':', 2).map(Number);
      let duration = result.value.duration.split(':', 2).map(Number);

      if (duration[0] == 12) {
        duration[0] == 0;
      }
      this.appointment.start.setHours(time[0]);
      this.appointment.start.setMinutes(time[1]);

      this.appointment.end = copy;
      if (time[1] + duration[1] <= 60) {
        this.appointment.end.setHours(time[0] + duration[0]);
        this.appointment.end.setMinutes(
          this.appointment.start.getMinutes() + duration[1]
        );
      } else {
        this.appointment.end.setHours(time[0] + duration[0] + 1);
        this.appointment.end.setMinutes(time[1] + duration[1] - 60);
      }

      this.appointment.price = result.value.price;
      if (this.appointment.dermatologist != null) {
        this.isValid = true;
      }
    });
  }

  addDermatologist(dermatologist: Dermatologist): void {
    this.appointment.dermatologist = dermatologist;
    this.openSnackBar(
      this.appointment.dermatologist.name + ' successfully added!',
      this.RESPONSE_OK
    );
    if (this.appointment.price != null) {
      this.isValid = true;
    }
  }

  createAppointment(): void {
    this.pharmacyAdminService
      .createPredefinedDermatologistAppointment(this.appointment)
      .subscribe((response) => {
        this.openSnackBar('Success!', this.RESPONSE_OK);
        this.appointment = {
          start: '' as any,
          end: '' as any,
          price: null as any,
          discount: null as any,
          done: null as any,
          pharmacy: null as any,
          dermatologist: null as any,
        };
        this.isValid = false;
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
