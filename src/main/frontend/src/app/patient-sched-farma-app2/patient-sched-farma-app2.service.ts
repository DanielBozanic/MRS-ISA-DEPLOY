import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PatientSchedFarmaApp2Service {

  constructor(private http: HttpClient) { }


  getPharmacistAppoinment(){
    return this.http.get<any>("http://localhost:8080/make_farmaceut_appointment/getAllAppointment")
  }

  addNewAppointment(data:any){
    return this.http.post<any>("http://localhost:8080/make_farmaceut_appointment/newAppointment", data).pipe(catchError(this.errorHandler));
  }


  errorHandler(error: HttpErrorResponse) {
    return throwError(error);
  }
}
