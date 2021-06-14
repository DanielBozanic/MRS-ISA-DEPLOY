import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse} from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class PatientSchedDermaAppService {

  private readonly getAppURL = 'http://localhost:8080/make_dermatologist_appointment/getPatientDerApp';
  private readonly deleteAppURL = 'http://localhost:8080/make_dermatologist_appointment/delete';

  constructor(private http: HttpClient) { }

  public getAllDerApp(){
    return this.http.get(this.getAppURL);
  }


  public cancelApp(data:any){
    return this.http.put<any>(this.deleteAppURL, data).pipe(catchError(this.errorHandler));
  }

  public errorHandler(error:HttpErrorResponse){
    return throwError(error);
  }
 
  
}
