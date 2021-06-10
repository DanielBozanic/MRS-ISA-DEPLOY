import { Injectable } from '@angular/core';


import { HttpClient } from '@angular/common/http';
import { AuthService } from '../login/auth.service';

@Injectable({
  providedIn: 'root'
})


export class PatientProfileNavbarService {
  
  readonly URL : string = "http://localhost:8080/patients/searchPatient";
  
  constructor(private http: HttpClient) {
    
  }

   getPatient(){
     return this.http.get(this.URL);
   }

   getDiscountByPatientCategory() {
     return this.http.get("http://localhost:8080/patients/getDiscountByPatientCategory");
   }
}
