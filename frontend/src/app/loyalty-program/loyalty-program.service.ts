import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoyaltyProgramService {

  constructor(private http: HttpClient) { }

  defineCategories(category: any) {
    return this.http.post<any>("http://localhost:8080/loyaltyProgram/defineCategories", category)
                              .pipe(catchError(this.errorHander));
  }

  getCategoryNames() {
    return this.http.get<any>("http://localhost:8080/loyaltyProgram/getCategoryNames");
  }

  definePointsForAppointmentAndConsulation(acp: any) {
    return this.http.post<any>("http://localhost:8080/loyaltyProgram/definePointsForAppointmentAndConsulation", acp)
                              .pipe(catchError(this.errorHander));
  }

  errorHander(error: HttpErrorResponse) {
    return throwError(error);
  }
}
