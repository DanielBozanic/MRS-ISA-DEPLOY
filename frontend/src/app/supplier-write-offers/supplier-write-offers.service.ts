import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SupplierWriteOffersService {

  constructor(private http: HttpClient) { }

  getMedicineSupply() {
    return this.http.get<any>("http://localhost:8080/suppliers/getMedicineSupply");
  }

  getOrders() {
    return this.http.get<any>("http://localhost:8080/suppliers/getOrders");
  }

  getPurchaseOrdersMedicine(purchaseOrderId: any) {
    return this.http.get<any>("http://localhost:8080/suppliers/getPurchaseOrdersMedicine/" + purchaseOrderId);
  }

  writeOffer(offer: any) {
    return this.http.post<any>("http://localhost:8080/suppliers/writeOffer", offer)
                              .pipe(catchError(this.errorHander));
  }

  errorHander(error: HttpErrorResponse) {
    return throwError(error);
  }
}