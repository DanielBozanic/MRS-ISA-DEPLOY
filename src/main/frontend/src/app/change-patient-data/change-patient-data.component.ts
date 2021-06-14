import { analyzeAndValidateNgModules } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PasswordValidator } from './passwordValidator';
import { ChangePatientDataService } from './change-patient-data.service';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { AuthService } from '../login/auth.service';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';

@Component({
  selector: 'app-change-patient-data',
  templateUrl: './change-patient-data.component.html',
  styleUrls: ['./change-patient-data.component.css']
})
export class ChangePatientDataComponent implements OnInit {
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  public checkOutForm: FormGroup;
  public updatePasswordForm: FormGroup;

  
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  verticalPosition: MatSnackBarVerticalPosition = "top";
  

  
  constructor(private formBuilder: FormBuilder, private service:ChangePatientDataService, private snackBar: MatSnackBar, private breakpointObserver: BreakpointObserver, private authService: AuthService){ 

    this.checkOutForm = this.formBuilder.group({
      username:[''],
      email:[''],
      name:[''],
      surname:[''], 
      address: [''],
      city: [''],
      country: [''],
      phoneNumber: ['', [Validators.maxLength(10), Validators.minLength(10)]],
    });

    this.updatePasswordForm = this.formBuilder.group({
      oldPassword:[''],
      password:[''],
      confirmPassword:['']
    }, {validator: PasswordValidator});

    this.service.dataShow().subscribe((data:any) => {this.fillDataForm(data);});


  }


  public hasError = (controlName: string, errorName: string, form: FormGroup) =>{
    return form.controls[controlName].hasError(errorName);
  }


  public submitForm(){
    this.service.changeData(this.checkOutForm.value).subscribe(
      response => {
        console.log(response);
        this.openSnackBar(response, this.RESPONSE_OK);
        this.service.dataShow().subscribe(
          data => {
            this.fillDataForm(data);
          });
      },
      error => {
        console.log(error);
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      }
    );
  }

  ngOnInit(): void {
  }


  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }



  fillDataForm(data: any) {
    this.checkOutForm.get('username')?.setValue(data.username);
    this.checkOutForm.get('email')?.setValue(data.email);
    this.checkOutForm.get('name')?.setValue(data.name);
    this.checkOutForm.get('surname')?.setValue(data.surname);
    this.checkOutForm.get('address')?.setValue(data.address);
    this.checkOutForm.get('city')?.setValue(data.city);
    this.checkOutForm.get('country')?.setValue(data.country);
    this.checkOutForm.get('phoneNumber')?.setValue(data.phoneNumber);
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
    this.service.updatePassword(this.updatePasswordForm.value).subscribe(
      response => {
        this.authService.logout();
      },
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      }
    )
  }

  logout() {
    this.authService.logout();
  }
}
