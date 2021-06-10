import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { AuthService } from '../login/auth.service';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private _http: HttpClient, private authService: AuthService) { }

  registerPatient(patient: any) {
    return this._http.post<any>("http://localhost:8080/registration/registerPatient", patient)
               .pipe(catchError(this.errorHander));
  }

  registerSystemAdministrator(systemAdministrator: any) {
    return this._http.post<any>("http://localhost:8080/registration/registerSystemAdministrator", systemAdministrator)
               .pipe(catchError(this.errorHander));
  }

  registerPharmacyAdministrator(pharmacyAdministrator: any) {
    return this._http.post<any>("http://localhost:8080/registration/registerPharmacyAdministrator", pharmacyAdministrator)
               .pipe(catchError(this.errorHander));
  }

  getPharmacies() {
    return this._http.get<any>("http://localhost:8080/pharmacy/getPharmacies")
  }

  registerDermatologist(dermatologist: any) {
    return this._http.post<any>("http://localhost:8080/registration/registerDermatologist", dermatologist)
               .pipe(catchError(this.errorHander));
  }

  registerSupplier(supplier: any) {
    return this._http.post<any>("http://localhost:8080/registration/registerSupplier", supplier)
               .pipe(catchError(this.errorHander));
  }

  errorHander(error: HttpErrorResponse) {
    return throwError(error);
  }
}