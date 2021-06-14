import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PatientSchedFarmaApp3Service {

  private readonly getAppURL = 'http://localhost:8080/make_farmaceut_appointment/getPatientPharApp';
  private readonly deleteAppURL = 'http://localhost:8080/make_farmaceut_appointment/delete';

  constructor(private http: HttpClient) { }

  public getAllPharApp(){
    return this.http.get(this.getAppURL);
  }

  public cancelApp(data:any){
    return this.http.put<any>(this.deleteAppURL, data).pipe(catchError(this.errorHandler));
  }

  public errorHandler(error:HttpErrorResponse){
    return throwError(error);
  }

}
