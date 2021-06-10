import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { AuthService } from '../login/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ChangePatientDataService {


  readonly URL : string = "http://localhost:8080/patients/searchPatient";

  username : string = "";

  constructor(private http: HttpClient) {
    
  }

  dataShow(){
    return this.http.get(this.URL);
  }

  changeData(data:any){
      return this.http.put<any>("http://localhost:8080/patients/changeData", data).pipe(catchError(this.errorHandler));
  }

  updatePassword(data:any){
    return this.http.put<any>("http://localhost:8080/patients/updatePassword", data).pipe(catchError(this.errorHandler));
  }

  errorHandler(error:HttpErrorResponse){
    return throwError(error);
  }
}
