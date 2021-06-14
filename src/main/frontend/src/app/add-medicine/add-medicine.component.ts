import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { AuthService } from '../login/auth.service';
import { AddMedicineService } from './add-medicine.service';
import { MedicinePointsValidator } from './MedicinePointsValidator';

@Component({
  selector: 'app-add-medicine',
  templateUrl: './add-medicine.component.html',
  styleUrls: ['./add-medicine.component.css']
})
export class AddMedicineComponent implements OnInit {

  constructor
  (
    private fb: FormBuilder, 
    private _addMedicineService: AddMedicineService, 
    private _snackBar: MatSnackBar,
    private authService: AuthService
    ) { }
  verticalPosition: MatSnackBarVerticalPosition = "top";

  addMedicineForm! : FormGroup;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  serverSubstituteMedicine : { id: string, name: string }[] = [];
  medicineTypes = [];
  medicineForms = [];

  ngOnInit(): void {
    this._addMedicineService.getMedicineForms().subscribe(
      data => {
        this.medicineForms = data;
      }
    );
    this._addMedicineService.getMedicineTypes().subscribe(
      data => {
        this.medicineTypes = data;
      }
    );
    this.addMedicineForm = this.fb.group(
      {
        medicineCode: ['', Validators.required],
        name: ['', Validators.required],
        medicineType: ['', Validators.required],
        form: ['', Validators.required],
        composition: ['', Validators.required],
        manufacturer: ['', Validators.required],
        points: ['', Validators.required],
        prescription: [true],
        substituteMedicineIds: [],
        addtionalComments: []
      }, {validator: MedicinePointsValidator}
    );
    this._addMedicineService.getSubstituteMedicine().subscribe(
      response => {
        let list : { id: string, name: string }[] = [];
        Object.keys(response).forEach((key : string) => {
          const v = response[key];
          list.push({id: key, name: v});
        });
        this.serverSubstituteMedicine = list;
      }
    );
  }

  public hasError = (controlName: string, errorName: string) => {
    return this.addMedicineForm.controls[controlName].hasError(errorName);
  }

  get points() {
    return this.addMedicineForm.get('points');
  }

  checkPoints() {
    if (this.addMedicineForm.hasError('pointsInvalid')) {
      this.addMedicineForm.get('points')?.setErrors([{'pointsInvalid': true}]);
    }
  }

  addMedicine() {
    this._addMedicineService.addMedicine(this.addMedicineForm.value).subscribe( 
    response => {
      this.openSnackBar(response, this.RESPONSE_OK);
      this.addMedicineForm.reset();
      this.addMedicineForm.get('prescription')?.setValue(true);
      this._addMedicineService.getSubstituteMedicine().subscribe(
        response1 => {
          let list : { id: string, name: string }[] = [];
          Object.keys(response1).forEach((key : string) => {
            const v = response1[key];
            list.push({id: key, name: v});
          });
          this.serverSubstituteMedicine = list;
        }
      );
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
