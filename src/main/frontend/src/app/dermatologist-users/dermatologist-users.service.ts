import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse} from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DermatologistUsersService {

  constructor(private _http: HttpClient) { }

  getPatients(name:string, surname:string){
    return this._http.get<any>("http://localhost:8080/patient-search/searchAllDermatologist/name=" + name + "surname=" + surname);
  }

  getAppointments(name:string, surname:string){
    return this._http.get<any>("http://localhost:8080/patient-search/searchAppointmentsDermatologist/name=" + name + "surname=" + surname);
  }

  errorHander(error: HttpErrorResponse) {
    return throwError(error);
  }
}