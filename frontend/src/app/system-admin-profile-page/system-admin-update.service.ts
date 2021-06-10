import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SystemAdminUpdateService {

  constructor(private http: HttpClient) { }

  updatePassword(passwords: any) {
    return this.http.put<any>("http://localhost:8080/systemAdmin/updatePassword", passwords);
  }
}
