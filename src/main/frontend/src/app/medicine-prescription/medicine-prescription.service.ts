import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MedicinePrescriptionService {

  constructor(private http: HttpClient) { }

  sendQrCode(qrCode: any) {
    return this.http.post<any>("http://localhost:8080/patients/sendQrCode", qrCode);
  }

  issueEReceipt(data:any) {
    return this.http.post<any>("http://localhost:8080/patients/issueEReceipt", data)
                                .pipe(catchError(this.errorHander));
  }

  errorHander(error: HttpErrorResponse) {
    return throwError(error);
  }
}
