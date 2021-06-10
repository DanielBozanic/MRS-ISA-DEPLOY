import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PharmacistPatientService {

  constructor(private _http: HttpClient) { }

    public getPatientsPharmacist(start:string){

        return this._http.get("http://localhost:8080/patient-search/searchPatientsPharmacist/start="+start)
    }
}