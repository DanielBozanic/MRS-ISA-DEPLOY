import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PharmacistUpdateService {

  constructor(private _http: HttpClient) { }

  getPharmacistData() {
    return this._http.get<any>("http://localhost:8080/pharmacist/get");
  }

  updatePharmacistData(pharmacist: any) {
    return this._http.put<any>("http://localhost:8080/pharmacist/update", pharmacist)
                              .pipe(catchError(this.errorHander));
  }

  updatePassword(passwords: any) {
    return this._http.put<any>("http://localhost:8080/pharmacist/updatePassword", passwords)
                              .pipe(catchError(this.errorHander));
  }

  errorHander(error: HttpErrorResponse) {
    return throwError(error);
  }
}