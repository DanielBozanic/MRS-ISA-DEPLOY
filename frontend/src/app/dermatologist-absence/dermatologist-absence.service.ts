import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DermatologistAbsenceService{
    constructor(private _http: HttpClient) { }

    makeAbsence(appointment: any) {
      return this._http.post<any>("http://localhost:8080/absence/dermatologist", appointment)
                 .pipe(catchError(this.errorHander));
    }

    errorHander(error: HttpErrorResponse) {
        return throwError(error);
    }
}