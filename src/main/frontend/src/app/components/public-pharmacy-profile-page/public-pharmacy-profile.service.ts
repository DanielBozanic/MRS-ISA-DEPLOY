import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Appointment } from 'src/app/models/appointment';
import { Employment } from 'src/app/models/employment';
import { MedicinePharmacy } from 'src/app/models/medicine-pharmacy';
import { Pharmacy } from 'src/app/user-complaint/user-complaint.component';

@Injectable({
  providedIn: 'root',
})
export class PublicPharmacyProfileService {
  constructor(private http: HttpClient) {}

  subscribeToPromotions(pharmacy: any) {
    return this.http.post<any>(
      'http://localhost:8080/patients/subscribeToPharamacy',
      pharmacy
    );
  }

  getMedicinePharmacy(pharmacyId: Number) {
    return this.http.get<MedicinePharmacy[]>(
      'http://localhost:8080/medicinePharmacy/getMedicineFromPharmacy/' +
        pharmacyId
    );
  }

  getDermatologistAppointments(pharmacyId: Number) {
    return this.http.get<Appointment[]>(
      'http://localhost:8080/appointment_creation/getPredefinedDermatologistAppointments/' +
        pharmacyId
    );
  }

  getPharmacyEmployees(pharmacyId: Number) {
    return this.http.get<Employment[]>(
      'http://localhost:8080/employment/getPharmacyEmployees/' + pharmacyId
    );
  }
}
