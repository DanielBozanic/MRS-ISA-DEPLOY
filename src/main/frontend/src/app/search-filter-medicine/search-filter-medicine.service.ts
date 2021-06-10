import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SearchFilterMedicineService {

  

  constructor(private http: HttpClient) { }

  getAllMedicinePharmacy() {
    return this.http.get<any>("http://localhost:8080/medicinePharmacy/getAllMedicinePharmacy")
  }

  searchMedicineByName(name:any) {
    return this.http.get<any>("http://localhost:8080/medicinePharmacy/searchMedicineByName/" + name);
  }

  getMedicineTypes() {
    return this.http.get<any>("http://localhost:8080/medicine/getMedicineTypes");
  }

  orderMedicine(medicine:any){
    return this.http.post<any>("http://localhost:8080/medicinePharmacy/orderMedicine", medicine);
  }

}

