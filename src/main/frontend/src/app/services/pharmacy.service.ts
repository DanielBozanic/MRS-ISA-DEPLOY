import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Pharmacy } from '../user-complaint/user-complaint.component';

@Injectable({
  providedIn: 'root',
})
export class PharmacyService {
  constructor(private httpClinet: HttpClient) {}

  public getAllPharmacies() {
    return this.httpClinet.get<Pharmacy[]>(
      'http://localhost:8080/pharmacy/publicGetPharmacies'
    );
  }
}
