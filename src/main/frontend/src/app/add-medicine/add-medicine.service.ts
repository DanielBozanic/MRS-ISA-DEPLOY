import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AddMedicineService {

  constructor(private _http: HttpClient) { }

  addMedicine(medicine: any) {
    return this._http.post<any>("http://localhost:8080/medicine/addMedicine", medicine)
               .pipe(catchError(this.errorHander));
  }

  getSubstituteMedicine() {
    return this._http.get<any>("http://localhost:8080/medicine/getMedicineList")
  }

  getMedicineForms() {
    return this._http.get<any>("http://localhost:8080/medicine/getMedicineForms");
  }

  getMedicineTypes() {
    return this._http.get<any>("http://localhost:8080/medicine/getMedicineTypes");
  }

  errorHander(error: HttpErrorResponse) {
    return throwError(error);
  }
}
