import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PatientOrdersMedicineService {

  
  constructor(private http: HttpClient) { }

  getAllMedicinePharmacy() {
    return this.http.get<any>("http://localhost:8080/medicinePharmacy/getAllMedicinePharmacy")
  }

  searchMedicineByName(name:any) {
    return this.http.get<any>("http://localhost:8080/medicinePharmacy/searchMedicineByName/" + name);
  }

  getMedicineTypes() {
    return this.http.get<any>("http://localhost:8080/medicine/getMedicineTypes");
  }

  orderMedicine(medicine:any){
    return this.http.put<any>("http://localhost:8080/medicinePharmacy/orderMedicine", medicine).pipe(catchError(this.errorHander));
  }

  errorHander(error: HttpErrorResponse) {
      return throwError(error);
  }
}
