import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PharmacyReviewService {

  private readonly getPharmaciesToRate = 'http://localhost:8080/rating/getPharmaciesToRate';
  private readonly putPharmaciesToRate = 'http://localhost:8080/rating/ratePharmacy';
  
  constructor(private http: HttpClient) { }

  public getAllPharmacies(){
    return this.http.get(this.getPharmaciesToRate);
  }

  public putRate(data:any){
    return this.http.put<any>(this.putPharmaciesToRate, data).pipe(catchError(this.errorHandler));
  }

  public errorHandler(error:HttpErrorResponse){
    return throwError(error);
  }
}
