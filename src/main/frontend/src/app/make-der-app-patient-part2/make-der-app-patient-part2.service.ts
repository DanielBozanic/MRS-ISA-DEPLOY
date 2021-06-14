import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MakeDerAppPatientPart2Service {

  private readonly getFreeAppURL = 'http://localhost:8080/make_dermatologist_appointment/getAllFreeDerApp';
  private readonly putAppURL = 'http://localhost:8080/make_dermatologist_appointment/send';
  
  constructor(private http: HttpClient) { }


  public getAllFreeDerApp(){
    return this.http.get(this.getFreeAppURL);
  }


  public post(data:any){
    return this.http.post<any>(this.putAppURL, data).pipe(catchError(this.errorHandler));
  }

  
  public errorHandler(error:HttpErrorResponse){
    return throwError(error);
  }


  
}
