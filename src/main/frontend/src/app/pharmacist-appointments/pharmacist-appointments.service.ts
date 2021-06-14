import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PharmacistAppointmentsService {

  constructor(private _http: HttpClient) { }

  getAppointmentsPharmacist(){
      return this._http.get("http://localhost:8080/calendar/calendarPharmacistToday")
  }

  getAppointmentWithId(id:number){
    return this._http.get("http://localhost:8080/appointment_creation/getPharmacist/id="+id);
  }
}