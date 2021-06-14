import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse} from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PharmacistAppointmentInfoService {

  constructor(private _http: HttpClient) { }

  setDone(appointment:any){
    return this._http.put<any>("http://localhost:8080/appointment_creation/setDonePharmacist", appointment);
  }

  getMeds(id:number, start:string){
    return this._http.get<any>("http://localhost:8080/medicinePharmacy/getMedicinePharmacist/pharmacy="+id+"start="+start);
  }

  getSubs(pharmacy:number, medicine:number, patient:number){
    return this._http.get<any>("http://localhost:8080/allergies/checkForAllergiesPharmacist/pharmacy=" + pharmacy + "medicine=" + medicine + "patient=" + patient);
  }

  endAppointment(appointment:any, meds:any, comments:string){
    return this._http.post<any>("http://localhost:8080/appointment_creation/endAppointmentPharmacist", {"appointment" : appointment, "meds":meds, "comments":comments}).pipe(catchError(this.errorHander));
  }

  makeAppointment(appointment: any) {
    return this._http.post<any>("http://localhost:8080/appointment_creation/pharmacist", appointment)
               .pipe(catchError(this.errorHander));
  }

getPharmacistData() {
    return this._http.get<any>("http://localhost:8080/pharmacist/get");
}

getPharmacyData(){
  return this._http.get<any>("http://localhost:8080/pharmacy/getPharmacyForPharmacist");
}

  errorHander(error: HttpErrorResponse) {
    return throwError(error);
  }
}