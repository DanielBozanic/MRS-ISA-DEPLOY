import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SupplierMedicineStockService {

  constructor(private http: HttpClient) { }

  getAllMedicine() {
    return this.http.get<any>("http://localhost:8080/suppliers/getAllMedicine");
  }

  updateMedicineStock(medicineSupply:any) {
    return this.http.post<any>("http://localhost:8080/suppliers/updateMedicineStock", medicineSupply)
                              .pipe(catchError(this.errorHander));
  }

  errorHander(error: HttpErrorResponse) {
    return throwError(error);
  }

}