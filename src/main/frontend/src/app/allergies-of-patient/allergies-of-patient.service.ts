import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AllergiesOfPatientService {

  private readonly getAllergies = 'http://localhost:8080/allergies/getAllAllergies';
  
  constructor(private http: HttpClient) { }

  public getAllAllergies(){
    return this.http.get(this.getAllergies);
  }

  

}
