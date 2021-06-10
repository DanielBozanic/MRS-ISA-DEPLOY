import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PharmacistReviewService {

  private readonly getPharmaciststToRate = 'http://localhost:8080/rating/getPharmacistsToRate';
  private readonly putPharmaciststToRate = 'http://localhost:8080/rating/ratePharmacist';

  constructor(private http: HttpClient) { }

  public getAllPharmacists(){
    return this.http.get(this.getPharmaciststToRate);
  }

  public putRate(data:any){
    return this.http.put<any>(this.putPharmaciststToRate, data).pipe(catchError(this.errorHandler));
  }

  public errorHandler(error:HttpErrorResponse){
    return throwError(error);
  }
}
