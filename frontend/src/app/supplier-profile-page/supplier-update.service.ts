import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SupplierUpdateService {

  constructor(private _http: HttpClient) 
  { 
  }

  getSupplierData() {
    return this._http.get<any>("http://localhost:8080/suppliers/getSupplierData");
  }

  updateSupplierData(supplier: any) {
    return this._http.put<any>("http://localhost:8080/suppliers/updateSupplierData", supplier)
                              .pipe(catchError(this.errorHander));
  }

  updatePassword(passwords: any) {
    return this._http.put<any>("http://localhost:8080/suppliers/updatePassword", passwords)
                              .pipe(catchError(this.errorHander));
  }

  errorHander(error: HttpErrorResponse) {
    return throwError(error);
  }
}
