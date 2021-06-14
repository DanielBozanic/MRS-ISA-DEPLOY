import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { PasswordValidator } from '../change-patient-data/passwordValidator';
import { AuthService } from '../login/auth.service';
import { SystemAdminUpdateService } from './system-admin-update.service';

@Component({
  selector: 'app-system-admin-profile-page',
  templateUrl: './system-admin-profile-page.component.html',
  styleUrls: ['./system-admin-profile-page.component.css']
})
export class SystemAdminProfilePageComponent implements OnInit {

  constructor
  (
    private authService: AuthService, 
    private router: Router,
    private fb: FormBuilder,
    private _systemAdminUpdateService: SystemAdminUpdateService,
    private _snackBar: MatSnackBar
  ) { }
  updatePasswordForm! : FormGroup;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";
  firstLogin = this.authService.getTokenData()?.firstLogin;

  ngOnInit(): void {
    if (this.firstLogin) {
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK, 20000);
    }
    this.updatePasswordForm = this.fb.group(
      {
        oldPassword: ['', Validators.required],
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required]
      }, {validator: PasswordValidator}
    );
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

  updatePassword() {
    this._systemAdminUpdateService.updatePassword(this.updatePasswordForm.value).subscribe(
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

  registerUsers() {
    if (!this.firstLogin) {
      this.router.navigate(['/register']);
    }
  }

  registerPharmacies() {
    if (!this.firstLogin) {
      this.router.navigate(['/registerPharmacies']);
    }
  }

  addMedicine() {
    if (!this.firstLogin) {
      this.router.navigate(['/addMedicine']);
    }
  }

  respondToComplaints() {
    if (!this.firstLogin) {
      this.router.navigate(['/respondToComplaints']);
    }
  }

  loyaltyProgram() {
    if (!this.firstLogin) {
      this.router.navigate(['/loyaltyProgram']);
    }
  }

  searchFilterMedicine() {
    if (!this.firstLogin) {
      this.router.navigate(['/searchFilterMedicine']);
    }
  }

  logout() {
    this.authService.logout();
  }

}
