import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PatientSchedFarmaAppService {

  constructor(private http: HttpClient) { }

  getPharmacistData() {
    return this.http.get<any>("http://localhost:8080/make_farmaceut_appointment/getAllEmploymentPharmacist");
  }


  getPharmacistAppoinment(){
    return this.http.get<any>("http://localhost:8080/make_farmaceut_appointment/getAllAppointment")
  }

  getCanceled(){
    return this.http.get<any>("http://localhost:8080/make_farmaceut_appointment/getAllCanceledApp")
  }

  errorHander(error: HttpErrorResponse) {
    return throwError(error);
  }
}
