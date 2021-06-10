import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse} from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DermatologistReviewService {

  private readonly getDermatologistToRate = 'http://localhost:8080/rating/getDermatologistToRate';
  private readonly putDermatologistToRate = 'http://localhost:8080/rating/rateDermatologist';
  
  constructor(private http: HttpClient) { }

  public getAllDermatologists(){
    return this.http.get(this.getDermatologistToRate);
  }

  public putRate(data:any){
    return this.http.put<any>(this.putDermatologistToRate, data).pipe(catchError(this.errorHandler));
  }


  public errorHandler(error:HttpErrorResponse){
    return throwError(error);
  }
}
