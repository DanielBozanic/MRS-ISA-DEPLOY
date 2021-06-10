import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PatientSubscribedPharmaciesService {

  constructor(private http: HttpClient) { }

  getSubscribedPharmacies() {
    return this.http.get<any>("http://localhost:8080/patients/getSubscribedPharmacies");
  }

  unsubscribeToPharamacy(pharmacy:any) {
    return this.http.put<any>("http://localhost:8080/patients/unsubscribeToPharamacy", pharmacy);
  }
}
