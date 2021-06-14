import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../login/auth.service';
import { PasswordValidator } from '../registration/validators/passwordValidator';
import { SupplierUpdateService } from './supplier-update.service';

@Component({
  selector: 'app-supplier-profile-page',
  templateUrl: './supplier-profile-page.component.html',
  styleUrls: ['./supplier-profile-page.component.css']
})
export class SupplierProfilePageComponent implements OnInit {

  updateForm! : FormGroup;
  updatePasswordForm! : FormGroup;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  constructor
  (
    private fb: FormBuilder, 
    private _supplierUpdateService: SupplierUpdateService, 
    private _snackBar: MatSnackBar,
    private authService: AuthService,
    private router: Router
  ) { }
  verticalPosition: MatSnackBarVerticalPosition = "top";
  firstLogin = this.authService.getTokenData()?.firstLogin;

  ngOnInit(): void {
    if (this.firstLogin) {
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK, 20000);
    }
    this.updateForm = this.fb.group(
      {
        username: [''],
        email: [''],
        name: [''],
        surname: [''],
        address: [''],
        city: [''],
        country: [''],
        phoneNumber: ['', [Validators.maxLength(10), Validators.minLength(10)]],
      }
    );
    this.updatePasswordForm = this.fb.group(
      {
        oldPassword: ['', Validators.required],
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required]
      }, {validator: PasswordValidator}
    );
    this._supplierUpdateService.getSupplierData().subscribe(
      data => {
        this.fillDataForm(data);
      }
    )
  }

  public hasError = (controlName: string, errorName: string, form: FormGroup) =>{
    return form.controls[controlName].hasError(errorName);
  }

  checkPasswords() {
    if (this.updatePasswordForm.hasError('passwordMismatch')) {
      this.updatePasswordForm.get('confirmPassword')?.setErrors([{'passwordMismatch': true}]);
    }
  }

  get confirmPassword() {
    return this.updatePasswordForm.get('confirmPassword');
  }

  update() {
    this._supplierUpdateService.updateSupplierData(this.updateForm.value).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK, 3000);
        this._supplierUpdateService.getSupplierData().subscribe(
          data => {
            this.fillDataForm(data);
          });
      },
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR, 3000);
      }
    )
  }

  updatePassword() {
    this._supplierUpdateService.updatePassword(this.updatePasswordForm.value).subscribe(
      response => {
        this.authService.logout();
      },
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR, 3000);
      }
    )
  }

  openSnackBar(msg: string, responseCode: number, duration: number) {
    this._snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? duration : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
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

  supplierWriteOffers() {
    if (!this.firstLogin) {
      this.router.navigate(["/supplierWriteOffers"]);
    }
  }

  supplierViewOffers() {
    if (!this.firstLogin) {
      this.router.navigate(["/supplierViewOffers"]);
    }
  }

  medicineStock() {
    if (!this.firstLogin) {
      this.router.navigate(["/medicineStock"]);
    }
  }

  logout() {
    this.authService.logout();
  }

}
