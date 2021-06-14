import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SupplierViewOffersService {

  constructor(private http: HttpClient) { }

  getOffersBySupplier() {
    return this.http.get<any>("http://localhost:8080/suppliers/getOffersBySupplier");
  }

  getPendingOffersBySupplier() {
    return this.http.get<any>("http://localhost:8080/suppliers/getPendingOffersBySupplier");
  }

  updateOffer(offer: any) {
    return this.http.put<any>("http://localhost:8080/suppliers/updateOffer", offer)
                              .pipe(catchError(this.errorHander));
  }

  errorHander(error: HttpErrorResponse) {
    return throwError(error);
  }
}