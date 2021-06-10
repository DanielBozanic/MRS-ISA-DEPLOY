import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm! : FormGroup;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  constructor
  (
    private fb: FormBuilder, 
    private router: Router,
    private authService: AuthService,
    private _snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group(
      {
        username: ['', Validators.required],
        password: ['', Validators.required],
      }
    );
    this.authService.createFirstSystemAdmin().subscribe();
  }

  public hasError = (controlName: string, errorName: string) =>{
    return this.loginForm.controls[controlName].hasError(errorName);
  }

  login() {
    this.authService.login(this.loginForm.value)
      .subscribe(
        data => {
          if (this.authService.getTokenData()?.role === "ROLE_SYSTEM_ADMIN") {
            this.router.navigate(['/systemAdminProfilePage']);
          } else if (this.authService.getTokenData()?.role === "ROLE_SUPPLIER") {
            this.router.navigate(['/supplierProfilePage']);
          } else if (this.authService.getTokenData()?.role === "ROLE_PATIENT") {
            this.router.navigate(['/UserHomePage']);
          } else if (this.authService.getTokenData()?.role === "ROLE_PHARMACY_ADMIN") {
            this.router.navigate(['/pharmacyAdmin']);
          } else if (this.authService.getTokenData()?.role === "ROLE_DERMATOLOGIST") {
            this.router.navigate(['/DermatologistHomePage']);
          } else if (this.authService.getTokenData()?.role === "ROLE_PHARMACIST") {
            this.router.navigate(['/PharmacistHomePage']);
          }
        },
        error => {
          if (error.status === 404) {
            this.openSnackBar("Invalid username/password!");
          } else if (error.status === 403) {
            this.openSnackBar("Email has not been verifed!");
          }
        });
    }

    openSnackBar(msg: string) {
      this._snackBar.open(msg, "x", {
        duration: 20000,
        verticalPosition: this.verticalPosition,
        panelClass: "back-red"
      });
    }
}
