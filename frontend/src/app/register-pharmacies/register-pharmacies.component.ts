import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { AuthService } from '../login/auth.service';
import { RegisterPharmaciesService } from './register-pharmacies.service';

@Component({
  selector: 'app-register-pharmacies',
  templateUrl: './register-pharmacies.component.html',
  styleUrls: ['./register-pharmacies.component.css']
})
export class RegisterPharmaciesComponent implements OnInit {

  pharmacyRegistrationForm! : FormGroup;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  constructor
  (
    private fb: FormBuilder, 
    private _registerPharmaciesService: RegisterPharmaciesService, 
    private _snackBar: MatSnackBar,
    private authService: AuthService
  ) { }
  verticalPosition: MatSnackBarVerticalPosition = "top";

  ngOnInit(): void {
    this.pharmacyRegistrationForm = this.fb.group(
      {
        name: ['', Validators.required],
        address: ['', Validators.required],
        city: ['', Validators.required],
        country: ['', Validators.required],
        description: [''],
      }
    );
  }

  public hasError = (controlName: string, errorName: string) =>{
    return this.pharmacyRegistrationForm.controls[controlName].hasError(errorName);
  }

  registerPharmacy() {
    this._registerPharmaciesService.registerPharmacy(this.pharmacyRegistrationForm.value).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK);
        this.pharmacyRegistrationForm.reset();
      },
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      }
    );
  }

  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

  logout() {
    this.authService.logout();
  }
}
