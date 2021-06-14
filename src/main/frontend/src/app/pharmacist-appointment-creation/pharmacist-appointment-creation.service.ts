import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PharmacistAppointmentCreationService{
    constructor(private _http: HttpClient) { }

    makeAppointment(appointment: any) {
        return this._http.post<any>("http://localhost:8080/appointment_creation/pharmacist", appointment)
                   .pipe(catchError(this.errorHander));
      }
    
    getPharmacistData() {
        return this._http.get<any>("http://localhost:8080/pharmacist/get");
    }

    getPharmacyData(){
      return this._http.get<any>("http://localhost:8080/pharmacy/getPharmacyForPharmacist");
    }

    errorHander(error: HttpErrorResponse) {
        return throwError(error);
    }
}