import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse} from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PharmacistUsersService {

  constructor(private _http: HttpClient) { }

  getPatients(name:string, surname:string){
    return this._http.get<any>("http://localhost:8080/patient-search/searchAllPharmacist/name=" + name + "surname=" + surname);
  }

  getAppointments(name:string, surname:string){
    return this._http.get<any>("http://localhost:8080/patient-search/searchAppointmentsPharmacist/name=" + name + "surname=" + surname);
  }

  errorHander(error: HttpErrorResponse) {
    return throwError(error);
  }
}