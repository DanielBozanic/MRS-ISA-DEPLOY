import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PatientPastDermaAppService {

  private readonly getAppURL = 'http://localhost:8080/make_dermatologist_appointment/getPastPatientDerApp';
  
  constructor(private http: HttpClient) { }

  public getAllDerApp(){
    return this.http.get(this.getAppURL);
  }
}
