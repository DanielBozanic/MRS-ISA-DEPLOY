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
  selector: 'app-pharmacy-admin',
  templateUrl: './pharmacy-admin.component.html',
  styleUrls: ['./pharmacy-admin.component.css'],
})
export class PharmacyAdminComponent implements OnInit {
  updateForm!: FormGroup;
  updatePasswordForm!: FormGroup;
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
      username: [''],
      email: [''],
      name: ['', Validators.required],
      surname: ['', Validators.required],
      address: ['', Validators.required],
      city: ['', Validators.required],
      country: ['', Validators.required],
      phoneNumber: [
        '',
        [
          Validators.required,
          Validators.maxLength(10),
          Validators.minLength(10),
        ],
      ],
    });
    this.updatePasswordForm = this.formBuilder.group(
      {
        oldPassword: ['', Validators.required],
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required],
      },
      { validator: PasswordValidator }
    );
    this.pharmacyAdminService.getPharmacyAdminData().subscribe((data) => {
      this.fillDataForm(data);
    });
  }
  update() {
    this.pharmacyAdminService
      .updatePharmacyAdminData(this.updateForm.value)
      .subscribe(
        (response) => {
          this.openSnackBar(response, this.RESPONSE_OK);
          this.pharmacyAdminService.getPharmacyAdminData().subscribe((data) => {
            this.fillDataForm(data);
          });
        },
        (error) => {
          this.openSnackBar(error.error, this.RESPONSE_ERROR);
        }
      );
  }
  updatePassword() {
    this.pharmacyAdminService
      .updatePassword(this.updatePasswordForm.value)
      .subscribe(
        (response) => {
          this.authService.logout();
        },
        (error) => {
          this.openSnackBar(error.error, this.RESPONSE_ERROR);
        }
      );
  }
  public hasError = (
    controlName: string,
    errorName: string,
    form: FormGroup
  ) => {
    return form.controls[controlName].hasError(errorName);
  };
  checkPasswords() {
    if (this.updatePasswordForm.hasError('passwordMismatch')) {
      this.updatePasswordForm
        .get('confirmPassword')
        ?.setErrors([{ passwordMismatch: true }]);
    }
  }
  get confirmPassword() {
    return this.updatePasswordForm.get('confirmPassword');
  }
  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, 'x', {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? 'back-green' : 'back-red',
    });
  }
  fillDataForm(data: any) {
    this.updateForm.get('username')?.setValue(data.username);
    this.updateForm.get('email')?.setValue(data.email);
    this.updateForm.get('name')?.setValue(data.name);
    this.updateForm.get('surname')?.setValue(data.surname);
    this.updateForm.get('address')?.setValue(data.address);
    this.updateForm.get('city')?.setValue(data.city);
    this.updateForm.get('country')?.setValue(data.country);
    this.updateForm.get('phoneNumber')?.setValue(data.phoneNumber);
  }

  back(): void {
    this.location.back();
  }

  logout() {
    this.authService.logout();
  }
}
