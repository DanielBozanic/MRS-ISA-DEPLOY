import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PenaltiesService {

  private readonly getPenalties = 'http://localhost:8080/rating/getPenalties';
  
  constructor(private http: HttpClient) { }

  public getPenaltiesF(){
    return this.http.get(this.getPenalties);
  }
}
