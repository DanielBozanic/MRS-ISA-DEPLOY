import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DermatologistAppointmentsService {

  constructor(private _http: HttpClient) { }

  getAppointmentsPharmacist(){
      return this._http.get("http://localhost:8080/calendar/calendarDermatologistToday")
  }

  getAppointmentWithId(id:number){
    return this._http.get("http://localhost:8080/appointment_creation/getDermatologist/id="+id);
  }
}