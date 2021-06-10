import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { PasswordValidator } from './validators/passwordValidator';
import { RegistrationService } from './registration.service';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { AuthService } from '../login/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor
  (
    private fb: FormBuilder, 
    private _registrationService: RegistrationService, 
    private _snackBar: MatSnackBar, 
    private authService: AuthService,
    private router: Router
  ) { }
  verticalPosition: MatSnackBarVerticalPosition = "top";

  registrationForm! : FormGroup;
  EMAIL_REGEX : string = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  systemAdmin : any = this.authService.getTokenData()?.role === 'ROLE_SYSTEM_ADMIN' ? true : false;
  users = ['System administrator', 'Dermatologist', 'Pharmacy administrator', 'Supplier'];
  selected =  this.authService.getTokenData()?.role === 'ROLE_SYSTEM_ADMIN' ? 'System administrator' : 'Patient';
  oldPasswordValue : any;
  oldUserTypeValue : any;
  selectedPharmacyAdmin = false;
  pharmacies = [];

  ngOnInit(): void {
    this.registrationForm = this.fb.group(
      {
        email: ['', [Validators.required, Validators.pattern(this.EMAIL_REGEX)]],
        username: ['', Validators.required],
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required],
        name: ['', Validators.required],
        surname: ['', Validators.required],
        address: ['', Validators.required],
        city: ['', Validators.required],
        country: ['', Validators.required],
        phoneNumber: ['', [Validators.required, Validators.maxLength(10), Validators.minLength(10)]],
        userType: [this.selected],
        pharmacy: []
      }, {validator: PasswordValidator}
    );
  }

  public hasError = (controlName: string, errorName: string) =>{
    return this.registrationForm.controls[controlName].hasError(errorName);
  }

  checkPasswords() {
    if (this.registrationForm.hasError('passwordMismatch')) {
      this.registrationForm.get('confirmPassword')?.setErrors([{'passwordMismatch': true}]);
    }
  }

  get confirmPassword() {
    return this.registrationForm.get('confirmPassword');
  }

  pharamcyField() {
    if (this.registrationForm.get('userType')?.value === 'Pharmacy administrator') {
      this._registrationService.getPharmacies().subscribe(
        data => {
          this.pharmacies = data;
          this.registrationForm.get('pharmacy')?.patchValue(this.pharmacies[0]);
          this.selectedPharmacyAdmin = true;
        }
      );
    } else {
      this.selectedPharmacyAdmin = false;
    }
  }

  register() {
    this.oldPasswordValue = this.registrationForm.get('confirmPassword');
    this.oldUserTypeValue = this.registrationForm.get('userType');
    this.registrationForm.removeControl('confirmPassword');
    this.registrationForm.removeControl('userType');
    if (this.systemAdmin) {
      if (this.oldUserTypeValue.value === 'System administrator') {
        this.registrationForm.removeControl('pharmacy');
        this._registrationService.registerSystemAdministrator(this.registrationForm.value).subscribe(
          response => {
            this.openSnackBar(response, this.RESPONSE_OK);
            this.registrationForm.reset();
          },
          error => {
              this.openSnackBar(error.error, this.RESPONSE_ERROR);
            
          }
        );
        this.registrationForm.addControl('pharmacy', new FormControl());
      } else if (this.oldUserTypeValue.value === 'Pharmacy administrator') {
        this._registrationService.registerPharmacyAdministrator(this.registrationForm.value).subscribe(
          response => {
            this.openSnackBar(response, this.RESPONSE_OK);
            this.selectedPharmacyAdmin = false;
            this.registrationForm.reset();
          },
          error => {
            this.openSnackBar(error.error, this.RESPONSE_ERROR);
            this.selectedPharmacyAdmin = true;
          }
        );
      } else if (this.oldUserTypeValue.value === 'Dermatologist') {
        this.registrationForm.removeControl('confirmPassword');
        this.registrationForm.removeControl('pharmacy');
        this._registrationService.registerDermatologist(this.registrationForm.value).subscribe(
          response => {
            this.openSnackBar(response, this.RESPONSE_OK);
            this.registrationForm.reset();
          },
          error => {
            this.openSnackBar(error.error, this.RESPONSE_ERROR);
          }
        );
        this.registrationForm.addControl('pharmacy', new FormControl());
      } else if (this.oldUserTypeValue.value === 'Supplier') {
        this.registrationForm.removeControl('confirmPassword');
        this.registrationForm.removeControl('pharmacy');
        this._registrationService.registerSupplier(this.registrationForm.value).subscribe(
          response => {
            this.openSnackBar(response, this.RESPONSE_OK);
            this.registrationForm.reset();
          },
          error => {
            this.openSnackBar(error.error, this.RESPONSE_ERROR);
          }
        );
        this.registrationForm.addControl('pharmacy', new FormControl());
      }
      this.selectedPharmacyAdmin = false;
      this.registrationForm.addControl('userType', new FormControl(this.oldUserTypeValue?.value));
    } else {
      this.registrationForm.removeControl('confirmPassword');
      this.registrationForm.removeControl('pharmacy');
      this._registrationService.registerPatient(this.registrationForm.value).subscribe(
        response => {
          this.openSnackBar(response, this.RESPONSE_OK);
          this.registrationForm.reset();
        },
        error => {
          this.openSnackBar(error.error, this.RESPONSE_ERROR);
        }
      );
      this.selectedPharmacyAdmin = false;
      this.registrationForm.addControl('pharmacy', new FormControl());
    }
    this.registrationForm.addControl('confirmPassword', new FormControl(this.oldPasswordValue?.value));
  }

  systemAdminProfilePage() {
    this.router.navigate(['/systemAdminProfilePage']);
  }

  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, "x", {
      duration: 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

  logout() {
    this.authService.logout();
  }
}