import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DermatologistChoosePredefinedService {

  constructor(private _http: HttpClient) { }

    public getAppointmentsPredefinedDermatologist(pharmacy:number){

        return this._http.get("http://localhost:8080/calendar/calendarDermatologistPredefined/pharmacy="+pharmacy)
    }

    public putAppointmentPredefinedDermatologist(id:any, patient:any){
        return this._http.put<any>("http://localhost:8080/appointment_creation/dermatologistPredefined/" + id, patient).pipe(catchError(this.errorHander));
    }

    errorHander(error: HttpErrorResponse) {
        return throwError(error);
    }
}