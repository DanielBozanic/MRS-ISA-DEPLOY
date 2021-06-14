import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PharmacistCalendarService {

  constructor(private _http: HttpClient) { }

  getAppointmentsPharmacist(){
      return this._http.get("http://localhost:8080/calendar/calendarPharmacist")
  }
}