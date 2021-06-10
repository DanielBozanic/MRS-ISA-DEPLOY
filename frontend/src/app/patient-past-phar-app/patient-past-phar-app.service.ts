import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PatientPastPharAppService {
  private readonly getAppURL = 'http://localhost:8080/make_farmaceut_appointment/getPastPatientPharApp';
  constructor(private http: HttpClient) { }

  public getAllPharApp(){
    return this.http.get(this.getAppURL);
  }
}
