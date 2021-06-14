import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Pharmacist } from '../models/pharmacist';
import { Medicine } from '../models/medicine';
import { Dermatologist } from '../models/dermatologist';
import { PharmacyAdmin } from '../models/pharmacy-admin';
import { catchError } from 'rxjs/operators';
import { MedicinePharmacy } from '../models/medicine-pharmacy';
import { Employment } from '../models/employment';
import { Pharmacy } from '../user-complaint/user-complaint.component';
import { PurchaseOrder } from '../models/purchase-order';
import { PurchaseOrderMedicine } from '../models/purchase-order-medicine';
import { Appointment } from '../models/appointment';
import { AppointmentDermatologist } from '../models/appointmentDermatologist';
import { MedicineNeeded } from '../models/medicineNeeded';
import { Absence } from '../models/absence';
import { Action } from '../models/action';
import { Promotion } from '../models/promotion';

@Injectable({
  providedIn: 'root',
})
export class PharmacyAdminService {
  private pharmacistsUrl!: string;
  private medicineUrl!: string;
  private dermatologistUrl!: string;

  constructor(private httpClient: HttpClient) {
    this.pharmacistsUrl =
      'http://localhost:8080/pharmacist/getUnemployedPharmacists';
    this.medicineUrl = 'http://localhost:8080/medicine/all';
    this.dermatologistUrl = 'http://localhost:8080/dermatologist/all';
  }

  public getPharmacyAdmin(pharmacyAdminId: String) {
    return this.httpClient.get<PharmacyAdmin>(
      'http://localhost:8080/pharmacyAdmin/' + pharmacyAdminId + '/findById'
    );
  }

  public deletePharmacist(
    pharmacist: Pharmacist,
    pharmacy: Pharmacy
  ): Observable<Pharmacist> {
    return this.httpClient.request<Pharmacist>(
      'delete',
      'http://localhost:8080/employment/deletePharmacistFromPharmacy/' +
        pharmacy.id,
      { body: pharmacist }
    );
  }

  public getUnemployedPharmacists(): Observable<Pharmacist[]> {
    return this.httpClient.get<Pharmacist[]>(this.pharmacistsUrl);
  }

  public createPharmacist(pharmacist: Pharmacist) {
    return this.httpClient.post<Pharmacist>(
      'http://localhost:8080/pharmacist/add',
      pharmacist
    );
  }

  public searchPharmacistArrayById(pharmacistId: String) {
    return this.httpClient.get<Pharmacist[]>(
      'http://localhost:8080/pharmacist/' + pharmacistId + '/findArrayById'
    );
  }

  public searchPharmacistByString(string: String) {
    return this.httpClient.get<Pharmacist[]>(
      'http://localhost:8080/pharmacist/' + string + '/findByString'
    );
  }
  public deleteMedicine(
    medicine: Medicine,
    pharmacy: Pharmacy
  ): Observable<Medicine> {
    return this.httpClient.request<Medicine>(
      'delete',
      'http://localhost:8080/medicinePharmacy/deleteMedicineFromPharmacy/' +
        pharmacy.id,
      { body: medicine }
    );
  }

  public getAllMedicine(): Observable<Medicine[]> {
    return this.httpClient.get<Medicine[]>(this.medicineUrl);
  }

  public getMedicineFromPharmacy(pharmacy: Pharmacy): Observable<Medicine[]> {
    return this.httpClient.get<Medicine[]>(
      'http://localhost:8080/medicine/' +
        pharmacy.id +
        '/getMedicineFromPharmacy'
    );
  }

  public getDermatologistsFromPharmacy(
    pharmacy: Pharmacy
  ): Observable<Dermatologist[]> {
    return this.httpClient.get<Dermatologist[]>(
      'http://localhost:8080/dermatologist/' +
        pharmacy.id +
        '/getDermatologistsFromPharmacy'
    );
  }
  public getPharmacistsFromPharmacy(
    pharmacy: Pharmacy
  ): Observable<Pharmacist[]> {
    return this.httpClient.get<Pharmacist[]>(
      'http://localhost:8080/pharmacist/' +
        pharmacy.id +
        '/getPharmacistsFromPharmacy'
    );
  }
  public createMedicine(medicine: Medicine) {
    return this.httpClient.post<Medicine>(
      'http://localhost:8080/medicine/addMedicine',
      medicine
    );
  }

  public searchMedicineArrayById(medicineId: String) {
    return this.httpClient.get<Medicine[]>(
      'http://localhost:8080/medicine/' + medicineId + '/findArrayById'
    );
  }

  public searchMedicineByString(string: String) {
    return this.httpClient.get<Medicine[]>(
      'http://localhost:8080/medicine/' + string + '/findByString'
    );
  }

  public updateMedicine(medicine: Medicine, medicineId: String) {
    return this.httpClient.put<Medicine>(
      'http://localhost:8080/medicine/' +
        medicineId +
        '/updateAdditionalComments',
      medicine
    );
  }

  public deleteDermatologist(
    dermatologist: Dermatologist,
    pharmacy: Pharmacy
  ): Observable<Dermatologist> {
    return this.httpClient.request<Dermatologist>(
      'delete',
      'http://localhost:8080/employment/deleteDermatologistFromPharmacy/' +
        pharmacy.id,
      { body: dermatologist }
    );
  }
  public getAllDermatologists(): Observable<Dermatologist[]> {
    return this.httpClient.get<Dermatologist[]>(this.dermatologistUrl);
  }

  public createDermatologist(dermatologist: Dermatologist) {
    return this.httpClient.post<Dermatologist>(
      'http://localhost:8080/dermatologist/add',
      dermatologist
    );
  }

  public addMedicineToPharmacy(medicinePharmacy: MedicinePharmacy) {
    return this.httpClient.post<MedicinePharmacy>(
      'http://localhost:8080/pharmacyAdmin/addMedicineToPharmacy',
      medicinePharmacy
    );
  }

  public addPharmacistToPharmacy(employment: Employment) {
    return this.httpClient.post<Employment>(
      'http://localhost:8080/pharmacyAdmin/addPharmacistToPharmacy',
      employment
    );
  }

  public addDermatologistToPharmacy(employment: Employment) {
    return this.httpClient.post<Employment>(
      'http://localhost:8080/pharmacyAdmin/addDermatologistToPharmacy',
      employment
    );
  }

  public searchDermatologistArrayById(dermatologistId: String) {
    return this.httpClient.get<Dermatologist[]>(
      'http://localhost:8080/dermatologist/' +
        dermatologistId +
        '/findArrayById'
    );
  }

  public searchDermatologistByString(string: String) {
    return this.httpClient.get<Dermatologist[]>(
      'http://localhost:8080/dermatologist/' + string + '/findByString'
    );
  }

  public updatePassword(passwords: any) {
    return this.httpClient
      .put<any>('http://localhost:8080/pharmacyAdmin/updatePassword', passwords)
      .pipe(catchError(this.errorHander));
  }

  public getPharmacyAdminData() {
    return this.httpClient.get<any>(
      'http://localhost:8080/pharmacyAdmin/getPharmacyAdminData'
    );
  }

  public getPharmacyData() {
    return this.httpClient.get<any>(
      'http://localhost:8080/pharmacyAdmin/getPharmacyData'
    );
  }

  public updatePharmacyAdminData(supplier: any) {
    return this.httpClient
      .put<any>(
        'http://localhost:8080/pharmacyAdmin/updatePharmacyAdminData',
        supplier
      )
      .pipe(catchError(this.errorHander));
  }

  public updatePharmacyData(supplier: any) {
    return this.httpClient
      .put<any>(
        'http://localhost:8080/pharmacyAdmin/updatePharmacyData',
        supplier
      )
      .pipe(catchError(this.errorHander));
  }

  public createPurchaseOrder(purchaseOrder: PurchaseOrder) {
    return this.httpClient.post<PurchaseOrder>(
      'http://localhost:8080/pharmacyAdmin/createPurchaseOrder',
      purchaseOrder
    );
  }

  public getActivePurchaseOrders(): Observable<PurchaseOrder[]> {
    return this.httpClient.get<PurchaseOrder[]>(
      'http://localhost:8080/purchaseOrder/activePurchaseOrders'
    );
  }

  public getPurchaseOrder(purchaseOrderId: Number) {
    return this.httpClient.get<PurchaseOrderMedicine[]>(
      'http://localhost:8080/purchaseOrder/' +
        purchaseOrderId +
        '/getPurchaseOrder'
    );
  }

  public createPredefinedDermatologistAppointment(appointment: Appointment) {
    return this.httpClient.post<AppointmentDermatologist>(
      'http://localhost:8080/appointment_creation/defineDermatologistAppointment',
      appointment
    );
  }

  public getMedicineInquiries(): Observable<MedicineNeeded[]> {
    return this.httpClient.get<MedicineNeeded[]>(
      'http://localhost:8080/medicine/getMedicineInquiries'
    );
  }

  public getAbsenceRequests(pharmacyId: String): Observable<Absence[]> {
    return this.httpClient.get<Absence[]>(
      'http://localhost:8080/absence/getAbsenceRequests/' + pharmacyId
    );
  }

  public approveAbsenceRequest(absence: Absence) {
    return this.httpClient.put<Absence>(
      'http://localhost:8080/absence/approveAbsenceRequest',
      absence
    );
  }

  public declineAbsenceRequest(absence: Absence) {
    return this.httpClient.request<Absence>(
      'delete',
      'http://localhost:8080/absence/declineAbsenceRequest/',
      { body: absence }
    );
  }

  public createAction(action: Action) {
    return this.httpClient.post<Action>(
      'http://localhost:8080/action/createAction',
      action
    );
  }

  public createPromotion(promotion: Promotion) {
    return this.httpClient.post<Promotion>(
      'http://localhost:8080/promotion/createPromotion',
      promotion
    );
  }

  errorHander(error: HttpErrorResponse) {
    return throwError(error);
  }
}
