import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DermatologistCalendarService {

  constructor(private _http: HttpClient) { }

  getAppointmentsDermatologist(){
      return this._http.get("http://localhost:8080/calendar/calendarDermatologist")
  }

  getEmployments(){
    return this._http.get("http://localhost:8080/appointment_creation/employmentsDermatologist")
  }

  getAppointmentsPharmacy(pharmacy:number){
    return this._http.get("http://localhost:8080/calendar/calendarDermatologist/pharmacy=" + pharmacy)
}
}