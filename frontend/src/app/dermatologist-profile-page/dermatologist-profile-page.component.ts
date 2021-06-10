import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../login/auth.service';
import { PasswordValidator } from '../registration/validators/passwordValidator';
import { DermatologistUpdateService } from './dermatologist-update.service';

@Component({
  selector: 'app-dermatologist-profile-page',
  templateUrl: './dermatologist-profile-page.component.html',
  styleUrls: ['./dermatologist-profile-page.component.css']
})
export class DermatologistProfilePageComponent implements OnInit {

  updateForm! : FormGroup;
  updatePasswordForm! : FormGroup;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  constructor(private fb: FormBuilder, private _pharmacistUpdateService: DermatologistUpdateService, private _snackBar: MatSnackBar, private authService: AuthService, public router : Router) { }
  verticalPosition: MatSnackBarVerticalPosition = "top";
  firstLogin = this.authService.getTokenData()?.firstLogin;

  ngOnInit(): void {
    this.updateForm = this.fb.group(
      {
        username: [''],
        email: [''],
        name: ['', Validators.required],
        surname: ['', Validators.required],
        address: ['', Validators.required],
        city: ['', Validators.required],
        country: ['', Validators.required],
        phoneNumber: ['', [Validators.required, Validators.maxLength(10), Validators.minLength(10)]],
        rating: [''],
      }, {validator: PasswordValidator}
    );
    this.updatePasswordForm = this.fb.group(
      {
        oldPassword: ['', Validators.required],
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required]
      }, {validator: PasswordValidator}
    );
    this._pharmacistUpdateService.getPharmacistData().subscribe(
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

  fillDataForm(data: any) {
    this.updateForm.get('username')?.setValue(data.username);
    this.updateForm.get('email')?.setValue(data.email);
    this.updateForm.get('name')?.setValue(data.name);
    this.updateForm.get('surname')?.setValue(data.surname);
    this.updateForm.get('address')?.setValue(data.address);
    this.updateForm.get('city')?.setValue(data.city);
    this.updateForm.get('country')?.setValue(data.country);
    this.updateForm.get('phoneNumber')?.setValue(data.phoneNumber);
    this.updateForm.get('rating')?.setValue(data.rating);
  }

  update() {
    console.log(this.updateForm.value);
    this._pharmacistUpdateService.updatePharmacistData(this.updateForm.value).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK);
        this._pharmacistUpdateService.getPharmacistData().subscribe(
          data => {
            this.fillDataForm(data);
          })
      },
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      }
    )
  }

  updatePassword() {
    this._pharmacistUpdateService.updatePassword(this.updatePasswordForm.value).subscribe(
      response => {
        this.authService.logout();
      },
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      }
    )
  }

  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

  back(){
    this.router.navigate(['/DermatologistHomePage']);
  }

  logout() {
    this.authService.logout();
  }
  
  profile(){
    this.router.navigate(["/DermatologistProfilePageComponent"]);
  }
  
  patients(){
    if(!this.firstLogin){
      this.router.navigate(["/DermatologistPatientComponent"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }
  
  allPatients(){
    if(!this.firstLogin){
      this.router.navigate(["/DermatologistUsersComponent"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }
  
  calendar(){
    if(!this.firstLogin){
      this.router.navigate(["/DermatologistCalendarComponent"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }
  
  absence(){
    if(!this.firstLogin){
      this.router.navigate(["/DermatologistAbsenceComponent"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }
  
  appointments(){
    if(!this.firstLogin){
      this.router.navigate(["/DermatologistAppointmentsComponent"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }
  
  reservations(){
    if(!this.firstLogin){
      this.router.navigate(["/DermatologistReservationsComponent"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }
  
  medicine(){
    if(!this.firstLogin){
      this.router.navigate(["/searchFilterMedicine"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }
}
