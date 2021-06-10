import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DermatologistAppointmentCreationService{
    constructor(private _http: HttpClient) { }

    makeAppointment(appointment: any) {
        return this._http.post<any>("http://localhost:8080/appointment_creation/dermatologist", appointment)
                   .pipe(catchError(this.errorHander));
      }

      getPharmacistData() {
        return this._http.get<any>("http://localhost:8080/dermatologist/get");
      }

    errorHander(error: HttpErrorResponse) {
        return throwError(error);
    }
}