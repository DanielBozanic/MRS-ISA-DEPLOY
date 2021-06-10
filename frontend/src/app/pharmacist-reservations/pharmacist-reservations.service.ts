import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse} from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PharmacistReservationsService {

  constructor(private _http: HttpClient) { }

  getReservations(id:number){
    return this._http.get<any>("http://localhost:8080/reservation/get/reservation="+id);
  }

  giveOut(reservation:any){
    return this._http.post<any>("http://localhost:8080/reservation/post", reservation).pipe(catchError(this.errorHander));
  }

  errorHander(error: HttpErrorResponse) {
    return throwError(error);
  }
}