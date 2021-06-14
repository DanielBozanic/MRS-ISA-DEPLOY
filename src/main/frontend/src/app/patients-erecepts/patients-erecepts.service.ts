import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PatientsEreceptsService {

  private readonly getAllergies = 'http://localhost:8080/allergies/getAllErecepts';
  
  constructor(private http: HttpClient) { }

  public getAllrecepts(){
    return this.http.get(this.getAllergies);
  }

}
