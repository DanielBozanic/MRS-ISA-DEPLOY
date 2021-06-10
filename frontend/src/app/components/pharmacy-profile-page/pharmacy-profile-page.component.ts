import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {
  MatSnackBar,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { PasswordValidator } from 'src/app/change-patient-data/passwordValidator';
import { AuthService } from 'src/app/login/auth.service';
import { PharmacyAdmin } from 'src/app/models/pharmacy-admin';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-pharmacy-profile-page',
  templateUrl: './pharmacy-profile-page.component.html',
  styleUrls: ['./pharmacy-profile-page.component.css'],
})
export class PharmacyProfilePageComponent implements OnInit {
  updateForm!: FormGroup;
  pharmacyAdmin!: PharmacyAdmin;
  pharmacyAdminId!: any;
  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;
  constructor(
    private pharmacyAdminService: PharmacyAdminService,
    private router: Router,
    private authService: AuthService,
    private _snackBar: MatSnackBar,
    private formBuilder: FormBuilder,
    private location: Location
  ) {}
  verticalPosition: MatSnackBarVerticalPosition = 'top';

  ngOnInit(): void {
    this.updateForm = this.formBuilder.group({
      name: ['', Validators.required],
      address: ['', Validators.required],
      appointmentPrice: ['', Validators.required],
      city: ['', Validators.required],
      country: ['', Validators.required],
      description: ['', Validators.required],
      rating: ['', Validators.required],
    });
    this.pharmacyAdminService.getPharmacyData().subscribe((data) => {
      this.fillDataForm(data);
    });
  }
  update() {
    this.pharmacyAdminService
      .updatePharmacyData(this.updateForm.value)
      .subscribe(
        (response) => {
          this.openSnackBar(response, this.RESPONSE_OK);
          this.pharmacyAdminService.getPharmacyData().subscribe((data) => {
            this.fillDataForm(data);
          });
        },
        (error) => {
          this.openSnackBar(error.error, this.RESPONSE_ERROR);
        }
      );
  }

  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, 'x', {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? 'back-green' : 'back-red',
    });
  }
  fillDataForm(data: any) {
    this.updateForm.get('name')?.setValue(data.name);
    this.updateForm.get('address')?.setValue(data.address);
    this.updateForm.get('appointmentPrice')?.setValue(data.appointmentPrice);
    this.updateForm.get('city')?.setValue(data.city);
    this.updateForm.get('country')?.setValue(data.country);
    this.updateForm.get('description')?.setValue(data.description);
    this.updateForm.get('rating')?.setValue(data.rating);
  }

  public hasError = (
    controlName: string,
    errorName: string,
    form: FormGroup
  ) => {
    return form.controls[controlName].hasError(errorName);
  };
  back(): void {
    this.location.back();
  }

  logout() {
    this.authService.logout();
  }
}
