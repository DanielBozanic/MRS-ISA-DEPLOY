import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MakeDerAppPatientService {
  private readonly getPharmaciesURL = 'http://localhost:8080/make_dermatologist_appointment/getAllPharamacies';

  
  constructor(private http: HttpClient) { }

  public getPharmacies(){
    return this.http.get(this.getPharmaciesURL);
  }

  
}
