import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { AuthService } from '../login/auth.service';
import { DiscountValidator } from './DiscountValidator';
import { LoyaltyProgramService } from './loyalty-program.service';
import { PointsValidator } from './PointsValidator';
import { RequiredNumberOfPointsValidator } from './RequiredNumberOfPointsValidator';

@Component({
  selector: 'app-loyalty-program',
  templateUrl: './loyalty-program.component.html',
  styleUrls: ['./loyalty-program.component.css']
})
export class LoyaltyProgramComponent implements OnInit {
  defineCategoriesForm!: FormGroup;
  appointmentAndConsultationForm!: FormGroup;
  categoryNames = [];
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";
  types = ['APPOINTMENT', 'CONSULTATION'];

  constructor
  (
    private fb: FormBuilder, 
    private _snackBar: MatSnackBar,
    private loyaltyProgramService: LoyaltyProgramService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.getCategoryNames();
    this.defineCategoriesForm = this.fb.group(
      {
        categoryName: ['', Validators.required],
        requiredNumberOfPoints: ['', Validators.required],
        discount: ['', Validators.required]
      }, {validator: [DiscountValidator, RequiredNumberOfPointsValidator]}
    );
    this.appointmentAndConsultationForm = this.fb.group(
      {
        type: ['', Validators.required],
        points: ['', Validators.required]
      }, {validator: PointsValidator}
    )
  }

  public hasError = (controlName: string, errorName: string, form: FormGroup) =>{
    return form.controls[controlName].hasError(errorName);
  }

  get requiredNumberOfPoints() {
    return this.defineCategoriesForm.get('requiredNumberOfPoints');
  }

  get discount() {
    return this.defineCategoriesForm.get('discount');
  }

  get points() {
    return this.appointmentAndConsultationForm.get('points');
  }

  checkRequiredNumberOfPoints() {
    if (this.defineCategoriesForm.hasError('requiredNumberOfPointsInvalid')) {
      this.defineCategoriesForm.get('requiredNumberOfPoints')?.setErrors([{'requiredNumberOfPointsInvalid': true}]);
    }
  }

  checkDiscount() {
    if (this.defineCategoriesForm.hasError('discountInvalid')) {
      this.defineCategoriesForm.get('discount')?.setErrors([{'discountInvalid': true}]);
    }
  }

  checkPoints() {
    if (this.appointmentAndConsultationForm.hasError('pointsInvalid')) {
      this.appointmentAndConsultationForm.get('points')?.setErrors([{'pointsInvalid': true}]);
    }
  }

  getCategoryNames() {
    this.loyaltyProgramService.getCategoryNames().subscribe(
      data => {
        this.categoryNames = data;
      }
    )
  }

  defineCategories() {
    this.loyaltyProgramService.defineCategories(this.defineCategoriesForm.value).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK);
      }, 
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      }
    )
  }

  definePointsForAppointmentAndConsulation() {
    this.loyaltyProgramService.definePointsForAppointmentAndConsulation(this.appointmentAndConsultationForm.value).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK);
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

  logout() {
    this.authService.logout();
  }
}
