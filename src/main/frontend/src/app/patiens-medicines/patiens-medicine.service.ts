import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PatiensMedicineService {

  constructor(private http: HttpClient){ }

  getAllMedicine() {
    return this.http.get<any>("http://localhost:8080/medicinePharmacy/getAllPatientsMedicines");
  }

  deleteMedicine(medicine:any){
    return this.http.put<any>("http://localhost:8080/medicinePharmacy/cancelMedicine", medicine).pipe(catchError(this.errorHander));
  }

  errorHander(error: HttpErrorResponse) {
      return throwError(error);
  }

}
