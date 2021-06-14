import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserComplaintService {
  private readonly getDermatologistURL = 'http://localhost:8080/complaint/getAllDermatologists';
  private readonly getPharmacistURL = 'http://localhost:8080/complaint/getAllPharmacist';
  private readonly getPharmacyURL = 'http://localhost:8080/complaint/getAllPharmacy';

  private readonly putUsernameDURL = 'http://localhost:8080/complaint/checkDermatologist';
  private readonly putUsernamePURL = 'http://localhost:8080/complaint/checkPharmacist';
  private readonly putUsernamePYURL = 'http://localhost:8080/complaint/checkPharmacy';

  private readonly postTextD = "http://localhost:8080/complaint/addComplaintToDermatologist";
  private readonly postTextP = "http://localhost:8080/complaint/addComplaintToPharmacist";
  private readonly postTextPY = "http://localhost:8080/complaint/addComplaintToPharmacy";

  constructor(private http: HttpClient) { }

  public getPharmacy(){
    return this.http.get(this.getPharmacyURL);
  }

  public getPharmacist(){
    return this.http.get(this.getPharmacistURL);
  }

  public getDermatologist(){
    return this.http.get(this.getDermatologistURL);
  }

  public checkD(data:any){
    return this.http.put<any>(this.putUsernameDURL, data).pipe(catchError(this.errorHandler));
  }

  public checkP(data:any){
    return this.http.put<any>(this.putUsernamePURL, data).pipe(catchError(this.errorHandler));
  }

  public checkPY(data:any){
    return this.http.put<any>(this.putUsernamePYURL, data).pipe(catchError(this.errorHandler));
  }


  public postD(data:any){
    return this.http.post<any>(this.postTextD, data).pipe(catchError(this.errorHandler));
  }

  public postP(data:any){
    return this.http.post<any>(this.postTextP, data).pipe(catchError(this.errorHandler));
  }

  public postPY(data:any){
    return this.http.post<any>(this.postTextPY, data).pipe(catchError(this.errorHandler));
  }



  public errorHandler(error:HttpErrorResponse){
    return throwError(error);
  }

}
