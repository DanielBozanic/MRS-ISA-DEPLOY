import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class PatientsComplaintsDataService {

  private readonly ULR = 'http://localhost:8080/complaint/getComplaints';  
  
  
  constructor(private http: HttpClient) { }

  public getCom(){
    return this.http.get(this.ULR);
  }

  
}
