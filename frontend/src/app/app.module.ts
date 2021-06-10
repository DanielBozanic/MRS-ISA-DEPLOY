import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularMaterialModule } from './angular_material.module';
import { FullCalendarModule } from '@fullcalendar/angular';
import { LayoutModule } from '@angular/cdk/layout';
import { MatSortModule } from '@angular/material/sort';
import { MaterialFileInputModule } from 'ngx-material-file-input';

import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import timeGridPlugin from '@fullcalendar/timegrid';

import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';

import { UserHomePageComponent } from './user-home-page/user-home-page.component';
import { PharmacistHomePageComponent } from './pharmacist-home-page/pharmacist-home-page.component';
import { DermatologistHomePageComponent } from './dermatologist-home-page/dermatologist-home-page.component';
import { PharmacistPatientComponent } from './pharmacist-patients/pharmacist-patient.component';
import { DermatologistPatientComponent } from './dermatologist-patients/dermatologist-patient.component';
import { PatientProfileNavbarComponent } from './patient-profile-navbar/patient-profile-navbar.component';
import { ChangePatientDataComponent } from './change-patient-data/change-patient-data.component';
import {
  DialogDataExampleDialogPharmacist,
  PharmacistCalendarComponent,
} from './pharmacist-calendar/pharmacist-calendar.component';
import {
  DermatologistCalendarComponent,
  DialogDataExampleDialogDermatologist,
} from './dermatologist-calendar/dermatologist-calendar.component';
import { AddMedicineComponent } from './add-medicine/add-medicine.component';
import { SystemAdminProfilePageComponent } from './system-admin-profile-page/system-admin-profile-page.component';
import { SupplierProfilePageComponent } from './supplier-profile-page/supplier-profile-page.component';
import { PharmacistAppointmentCreationComponent } from './pharmacist-appointment-creation/pharmacist-appointment-creation.component';
import {
  DermatologistChoosePredefinedComponent,
  DialogPredefined,
} from './dermatologist-choose-predefined/dermatologist-choose-predefined.component';
import { DermatologistAppointmentCreationComponent } from './dermatologist-appointment-creation/dermatologist-appointment-creation.component';
import { SearchPharmacyComponent } from './search-pharmacy/search-pharmacy.component';
import { RegisterPharmaciesComponent } from './register-pharmacies/register-pharmacies.component';

import { PharmacyAdminComponent } from './components/pharmacy-admin/pharmacy-admin.component';
import { ListOfPharmacistsComponent } from './components/list-of-pharmacists/list-of-pharmacists.component';
import { AddNewPharmacistComponent } from './components/add-new-pharmacist/add-new-pharmacist.component';
import { AddNewMedicineComponent } from './components/add-new-medicine/add-new-medicine.component';
import { ListOfMedicineComponent } from './components/list-of-medicine/list-of-medicine.component';
import { AddNewDermatologistComponent } from './components/add-new-dermatologist/add-new-dermatologist.component';
import { ListOfDermatologistsComponent } from './components/list-of-dermatologists/list-of-dermatologists.component';
import { UpdateMedicineComponent } from './components/update-medicine/update-medicine.component';
import { PharmacistProfilePageComponent } from './pharmacist-profile-page/pharmacist-profile-page.component';
import { DermatologistProfilePageComponent } from './dermatologist-profile-page/dermatologist-profile-page.component';
import { AuthService } from './login/auth.service';
import { TokenInterceptor } from './login/TokenInterceptor';
import { SystemAdminRoutes } from './user-routes/SystemAdminRoutes';
import { PharmacistRoutes } from './user-routes/PharmacistRoutes';
import { DermatologistRoutes } from './user-routes/DermatologistRoutes';
import { SupplierRoutes } from './user-routes/SupplierRoutes';
import { PharmacyAdminRoutes } from './user-routes/PharmacyAdminRoutes';
import { PatientRoutes } from './user-routes/PatientRoutes';
import { UserComplaintComponent } from './user-complaint/user-complaint.component';
import { PatientsComplaintsDataComponent } from './patients-complaints-data/patients-complaints-data.component';
import { SupplierWriteOffersComponent } from './supplier-write-offers/supplier-write-offers.component';

import {
  DialogStartPharmacist,
  PharmacistAppointmentsComponent,
} from './pharmacist-appointments/pharmacist-appointments.component';
import { PharmacistAppointmentInfoComponent } from './pharmacist-appointment-info/pharmacist-appointment-info.component';
import {
  DermatologistAppointmentsComponent,
  DialogStartDermatologist,
} from './dermatologist-appointments/dermatologist-appointments.component';
import { DermatologistAppointmentInfoComponent } from './dermatologist-appointment-info/dermatologist-appointment-info.component';
import { SupplierViewOffersComponent } from './supplier-view-offers/supplier-view-offers.component';
import { RespondToComplaintsComponent } from './respond-to-complaints/respond-to-complaints.component';

import { MakeDerAppPatientComponent } from './make-der-app-patient/make-der-app-patient.component';
import { MakeDerAppPatientPart2Component } from './make-der-app-patient-part2/make-der-app-patient-part2.component';
import { PatientSchedDermaAppComponent } from './patient-sched-derma-app/patient-sched-derma-app.component';
import { PharmacistAbsenceComponent } from './pharmacist-absence/pharmacist-absence.component';
import { DermatologistAbsenceComponent } from './dermatologist-absence/dermatologist-absence.component';
import { SearchFilterMedicineComponent } from './search-filter-medicine/search-filter-medicine.component';
import { LoyaltyProgramComponent } from './loyalty-program/loyalty-program.component';
import { PharmacistReservationsComponent } from './pharmacist-reservations/pharmacist-reservations.component';
import { SupplierMedicineStockComponent } from './supplier-medicine-stock/supplier-medicine-stock.component';
import { PatientSubscribedPharmaciesComponent } from './patient-subscribed-pharmacies/patient-subscribed-pharmacies.component';
import { PatientSchedFarmaAppComponent } from './patient-sched-farma-app/patient-sched-farma-app.component';

import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';
import { PatientSchedFarmaApp2Component } from './patient-sched-farma-app2/patient-sched-farma-app2.component';
import { PatientSchedFarmaApp3Component } from './patient-sched-farma-app3/patient-sched-farma-app3.component';
import { PatientOrdersMedicineComponent } from './patient-orders-medicine/patient-orders-medicine.component';
import { PatiensMedicinesComponent } from './patiens-medicines/patiens-medicines.component';

import { AddMedicineToPharmacyComponent } from './components/add-medicine-to-pharmacy/add-medicine-to-pharmacy.component';
import { AddPharmacistToPharmacyComponent } from './components/add-pharmacist-to-pharmacy/add-pharmacist-to-pharmacy.component';
import { AddDermatologistToPharmacyComponent } from './components/add-dermatologist-to-pharmacy/add-dermatologist-to-pharmacy.component';

import {
  DialogStartPharmacistPatients,
  PharmacistUsersComponent,
} from './pharmacist-users/pharmacist-users.component';
import {
  DermatologistUsersComponent,
  DialogStartDermatologistPatients,
} from './dermatologist-users/dermatologist-users.component';
import { MedicinePrescriptionComponent } from './medicine-prescription/medicine-prescription.component';
import { PharmacyProfilePageComponent } from './components/pharmacy-profile-page/pharmacy-profile-page.component';

import { ReviewComponent } from './review/review.component';
import { DermatologistReviewComponent } from './dermatologist-review/dermatologist-review.component';
import { PharmacistReviewComponent } from './pharmacist-review/pharmacist-review.component';
import { PharmacyReviewComponent } from './pharmacy-review/pharmacy-review.component';
import { MedicineReviewComponent } from './medicine-review/medicine-review.component';

import { MedicinePurchaseOrderComponent } from './components/medicine-purchase-order/medicine-purchase-order.component';
import { AddMedicineToCartPopupComponent } from './components/add-medicine-to-cart-popup/add-medicine-to-cart-popup.component';
import { SubmitPurchaseOrderPopupComponent } from './components/submit-purchase-order-popup/submit-purchase-order-popup.component';

import { PatientPastDermaAppComponent } from './patient-past-derma-app/patient-past-derma-app.component';
import { PatientPastPharAppComponent } from './patient-past-phar-app/patient-past-phar-app.component';
import { AllergiesComponent } from './allergies/allergies.component';
import { PenaltiesComponent } from './penalties/penalties.component';


import { ListOfPurchaseOrdersComponent } from './components/list-of-purchase-orders/list-of-purchase-orders.component';
import { DisplayActivePurchaseOrdersComponent } from './components/display-active-purchase-orders/display-active-purchase-orders.component';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PredefineDermatologistAppointmentComponent } from './components/predefine-dermatologist-appointment/predefine-dermatologist-appointment.component';
import { DermatologistAppointmentPopupComponent } from './components/dermatologist-appointment-popup/dermatologist-appointment-popup.component';
import { PublicPharmacyProfilePageComponent } from './components/public-pharmacy-profile-page/public-pharmacy-profile-page.component';
import { MedicineInquiriesComponent } from './components/medicine-inquiries/medicine-inquiries.component';
import { AllergiesOfPatientComponent } from './allergies-of-patient/allergies-of-patient.component';


FullCalendarModule.registerPlugins([
  dayGridPlugin,
  interactionPlugin,
  timeGridPlugin,
]);

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    UserHomePageComponent,
    PharmacistHomePageComponent,
    DermatologistHomePageComponent,
    PharmacistPatientComponent,
    DermatologistPatientComponent,
    PatientProfileNavbarComponent,
    ChangePatientDataComponent,
    PharmacistCalendarComponent,
    DermatologistCalendarComponent,
    AddMedicineComponent,
    SystemAdminProfilePageComponent,
    SupplierProfilePageComponent,
    PharmacyAdminComponent,
    ListOfPharmacistsComponent,
    AddNewPharmacistComponent,
    AddNewMedicineComponent,
    ListOfMedicineComponent,
    AddNewDermatologistComponent,
    ListOfDermatologistsComponent,
    PharmacistAppointmentCreationComponent,
    DermatologistAppointmentCreationComponent,
    DermatologistChoosePredefinedComponent,
    DermatologistChoosePredefinedComponent,
    SearchPharmacyComponent,
    RegisterPharmaciesComponent,
    UpdateMedicineComponent,
    PharmacistProfilePageComponent,
    DermatologistProfilePageComponent,
    UserComplaintComponent,
    PatientsComplaintsDataComponent,
    SupplierWriteOffersComponent,
    PharmacistAppointmentsComponent,
    PharmacistAppointmentInfoComponent,
    DermatologistAppointmentsComponent,
    DermatologistAppointmentInfoComponent,
    SupplierViewOffersComponent,
    RespondToComplaintsComponent,
    SearchFilterMedicineComponent,
    DialogDataExampleDialogPharmacist,
    DialogDataExampleDialogDermatologist,
    DialogPredefined,
    DialogStartPharmacist,
    DialogStartDermatologist,
    MakeDerAppPatientComponent,
    MakeDerAppPatientPart2Component,
    PatientSchedDermaAppComponent,
    PharmacistAbsenceComponent,
    DermatologistAbsenceComponent,
    LoyaltyProgramComponent,
    PharmacistReservationsComponent,
    SupplierMedicineStockComponent,
    PatientSubscribedPharmaciesComponent,

    PatientSchedFarmaAppComponent,
    PatientSchedFarmaApp2Component,
    PatientSchedFarmaApp3Component,
    PatientOrdersMedicineComponent,
    PatiensMedicinesComponent,

    AddMedicineToPharmacyComponent,
    AddPharmacistToPharmacyComponent,
    AddDermatologistToPharmacyComponent,
    PharmacistUsersComponent,
    DermatologistUsersComponent,
    MedicinePrescriptionComponent,
    PharmacyProfilePageComponent,

    ReviewComponent,
    DermatologistReviewComponent,
    PharmacistReviewComponent,
    PharmacyReviewComponent,
    MedicineReviewComponent,

    DialogStartDermatologistPatients,
    DialogStartPharmacistPatients,
    MedicinePurchaseOrderComponent,
    AddMedicineToCartPopupComponent,
    SubmitPurchaseOrderPopupComponent,

    PatientPastDermaAppComponent,
    PatientPastPharAppComponent,
    AllergiesComponent,
    PenaltiesComponent,


    ListOfPurchaseOrdersComponent,
    DisplayActivePurchaseOrdersComponent,
    PredefineDermatologistAppointmentComponent,
    DermatologistAppointmentPopupComponent,
    PublicPharmacyProfilePageComponent,
    MedicineInquiriesComponent,
    AllergiesOfPatientComponent,

  ],
  imports: [
    BrowserModule,
    FullCalendarModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AngularMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    MatSortModule,
    LayoutModule,

    NgxMaterialTimepickerModule,

    MaterialFileInputModule,

    RouterModule.forRoot([
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'register', component: RegistrationComponent },
      { path: 'login', component: LoginComponent },
      {
        path: 'UserHomePage',
        canActivate: [PatientRoutes],
        component: UserHomePageComponent,
      },
      {
        path: 'PharmacistHomePage',
        canActivate: [PharmacistRoutes],
        component: PharmacistHomePageComponent,
      },
      {
        path: 'DermatologistHomePage',
        canActivate: [DermatologistRoutes],
        component: DermatologistHomePageComponent,
      },
      {
        path: 'PharmacistPatientComponent',
        canActivate: [PharmacistRoutes],
        component: PharmacistPatientComponent,
      },
      {
        path: 'DermatologistPatientComponent',
        canActivate: [DermatologistRoutes],
        component: DermatologistPatientComponent,
      },
      {
        path: 'PharmacistCalendarComponent',
        canActivate: [PharmacistRoutes],
        component: PharmacistCalendarComponent,
      },
      {
        path: 'DermatologistCalendarComponent',
        canActivate: [DermatologistRoutes],
        component: DermatologistCalendarComponent,
      },
      {
        path: 'addMedicine',
        canActivate: [SystemAdminRoutes],
        component: AddMedicineComponent,
      },
      {
        path: 'respondToComplaints',
        canActivate: [SystemAdminRoutes],
        component: RespondToComplaintsComponent,
      },
      {
        path: 'loyaltyProgram',
        canActivate: [SystemAdminRoutes],
        component: LoyaltyProgramComponent,
      },
      {
        path: 'systemAdminProfilePage',
        canActivate: [SystemAdminRoutes],
        component: SystemAdminProfilePageComponent,
      },
      {
        path: 'supplierProfilePage',
        canActivate: [SupplierRoutes],
        component: SupplierProfilePageComponent,
      },
      {
        path: 'supplierWriteOffers',
        canActivate: [SupplierRoutes],
        component: SupplierWriteOffersComponent,
      },
      {
        path: 'supplierViewOffers',
        canActivate: [SupplierRoutes],
        component: SupplierViewOffersComponent,
      },
      {
        path: 'medicineStock',
        canActivate: [SupplierRoutes],
        component: SupplierMedicineStockComponent,
      },
      {
        path: 'pharmacyAdmin',
        canActivate: [PharmacyAdminRoutes],
        component: PharmacyAdminComponent,
      },
      {
        path: 'listOfPharmacists',
        canActivate: [PharmacyAdminRoutes],
        component: ListOfPharmacistsComponent,
      },
      {
        path: 'addNewPharmacist',
        canActivate: [PharmacyAdminRoutes],
        component: AddNewPharmacistComponent,
      },
      {
        path: 'listOfMedicine',
        canActivate: [PharmacyAdminRoutes],
        component: ListOfMedicineComponent,
      },
      {
        path: 'addNewMedicine',
        canActivate: [PharmacyAdminRoutes],
        component: AddNewMedicineComponent,
      },
      {
        path: 'listOfDermatologists',
        canActivate: [PharmacyAdminRoutes],
        component: ListOfDermatologistsComponent,
      },
      {
        path: 'addNewDermatologist',
        canActivate: [PharmacyAdminRoutes],
        component: AddNewDermatologistComponent,
      },
      {
        path: 'PatientProfile',
        canActivate: [PatientRoutes],
        component: PatientProfileNavbarComponent,
      },
      {
        path: 'PatientChangeData',
        canActivate: [PatientRoutes],
        component: ChangePatientDataComponent,
      },
      {
        path: 'PharmacistAppointmentCreationComponent',
        canActivate: [PharmacistRoutes],
        component: PharmacistAppointmentCreationComponent,
      },
      {
        path: 'DermatologistAppointmentCreationComponent',
        canActivate: [DermatologistRoutes],
        component: DermatologistAppointmentCreationComponent,
      },
      {
        path: 'DermatologistChoosePredefinedComponent',
        canActivate: [DermatologistRoutes],
        component: DermatologistChoosePredefinedComponent,
      },
      {
        path: 'searchPharmacy',
        component: SearchPharmacyComponent,
      },
      {
        path: 'registerPharmacies',
        canActivate: [SystemAdminRoutes],
        component: RegisterPharmaciesComponent,
      },
      {
        path: 'updateMedicine',
        canActivate: [PharmacyAdminRoutes],
        component: UpdateMedicineComponent,
      },
      {
        path: 'PharmacistProfilePageComponent',
        canActivate: [PharmacistRoutes],
        component: PharmacistProfilePageComponent,
      },
      {
        path: 'DermatologistProfilePageComponent',
        canActivate: [DermatologistRoutes],
        component: DermatologistProfilePageComponent,
      },
      {
        path: 'userComplaint',
        canActivate: [PatientRoutes],
        component: UserComplaintComponent,
      },
      {
        path: 'patientsComplaintsData',
        canActivate: [PatientRoutes],
        component: PatientsComplaintsDataComponent,
      },
      {
        path: 'PharmacistAppointmentsComponent',
        canActivate: [PharmacistRoutes],
        component: PharmacistAppointmentsComponent,
      },
      {
        path: 'PharmacistAppointmentInfoComponent',
        canActivate: [PharmacistRoutes],
        component: PharmacistAppointmentInfoComponent,
      },
      {
        path: 'DermatologistAppointmentsComponent',
        canActivate: [DermatologistRoutes],
        component: DermatologistAppointmentsComponent,
      },
      {
        path: 'DermatologistAppointmentInfoComponent',
        canActivate: [DermatologistRoutes],
        component: DermatologistAppointmentInfoComponent,
      },
      {
        path: 'MakeDermatologistAppointment',
        canActivate: [PatientRoutes],
        component: MakeDerAppPatientComponent,
      },
      {
        path: 'MakeDermatologistAppointmentPart2',
        canActivate: [PatientRoutes],
        component: MakeDerAppPatientPart2Component,
      },
      {
        path: 'PatientScheduledDermaApp',
        canActivate: [PatientRoutes],
        component: PatientSchedDermaAppComponent,
      },
      {
        path: 'subscribedPharmacies',
        canActivate: [PatientRoutes],
        component: PatientSubscribedPharmaciesComponent,
      },
      {
        path: 'PharmacistAbsenceComponent',
        canActivate: [PharmacistRoutes],
        component: PharmacistAbsenceComponent,
      },
      {
        path: 'DermatologistAbsenceComponent',
        canActivate: [DermatologistRoutes],
        component: DermatologistAbsenceComponent,
      },
      {
        path: 'searchFilterMedicine',
        component: SearchFilterMedicineComponent,
      },
      {
        path: 'PharmacistReservationsComponent',
        canActivate: [PharmacistRoutes],
        component: PharmacistReservationsComponent,
      },
      {
        path: 'PatientScheduleFarmaApp',
        canActivate: [PatientRoutes],
        component: PatientSchedFarmaAppComponent,
      },
      {
        path: 'PatientScheduleFarmaApp2',
        canActivate: [PatientRoutes],
        component: PatientSchedFarmaApp2Component,
      },
      {
        path: 'PatientScheduleFarmaApp3',
        canActivate: [PatientRoutes],
        component: PatientSchedFarmaApp3Component,
      },
      {
        path: 'PatientOrdersMedicine',
        canActivate: [PatientRoutes],
        component: PatientOrdersMedicineComponent,
      },
      {
        path: 'PatiensMedicines',
        canActivate: [PatientRoutes],
        component: PatiensMedicinesComponent,
      },
      {
        path: 'addMedicineToPharmacy',
        canActivate: [PharmacyAdminRoutes],
        component: AddMedicineToPharmacyComponent,
      },
      {
        path: 'addPharmacistToPharmacy',
        canActivate: [PharmacyAdminRoutes],
        component: AddPharmacistToPharmacyComponent,
      },
      {
        path: 'addDermatologistToPharmacy',
        canActivate: [PharmacyAdminRoutes],
        component: AddDermatologistToPharmacyComponent,
      },
      {
        path: 'PharmacistUsersComponent',
        canActivate: [PharmacistRoutes],
        component: PharmacistUsersComponent,
      },
      {
        path: 'DermatologistUsersComponent',
        canActivate: [DermatologistRoutes],
        component: DermatologistUsersComponent,
      },
      {
        path: 'medicinePrescription',
        canActivate: [PatientRoutes],
        component: MedicinePrescriptionComponent,
      },
      {
        path: 'pharmacyProfilePage',
        component: PharmacyProfilePageComponent,
      },
      {
        path: 'Review',
        canActivate: [PatientRoutes],
        component: ReviewComponent,
      },
      {
        path: 'DermatologistReview',
        canActivate: [PatientRoutes],
        component: DermatologistReviewComponent,
      },
      {
        path: 'PharmacistReview',
        canActivate: [PatientRoutes],
        component: PharmacistReviewComponent,
      },
      {
        path: 'PharmacyReview',
        canActivate: [PatientRoutes],
        component: PharmacyReviewComponent,
      },
      {
        path: 'MedicineReview',
        canActivate: [PatientRoutes],
        component: MedicineReviewComponent,
      },
      {
        path: 'medicinePurchaseOrder',
        canActivate: [PharmacyAdminRoutes],
        component: MedicinePurchaseOrderComponent,
      },
      {
        path: 'PatientPastDermaApp',
        canActivate: [PatientRoutes],
        component: PatientPastDermaAppComponent,
      },
      {
        path: 'PatientPastPharApp',
        canActivate: [PatientRoutes],
        component: PatientPastPharAppComponent,
      },
      {
        path: 'PatientPastPharApp',
        canActivate: [PatientRoutes],
        component: PatientPastPharAppComponent,
      },
      {
        path: 'Allergies',
        canActivate: [PatientRoutes],
        component: AllergiesComponent,
      },
      {
        path: 'Penalties',
        canActivate: [PatientRoutes],
        component: PenaltiesComponent,
      },
      {
        path: 'activePurchaseOrders',
        canActivate: [PharmacyAdminRoutes],
        component: ListOfPurchaseOrdersComponent,
      },
      {
        path: 'predefineDermatologistAppointment',
        canActivate: [PharmacyAdminRoutes],
        component: PredefineDermatologistAppointmentComponent,
      },
      {
        path: 'publicPharmacyProfilePage',
        component: PublicPharmacyProfilePageComponent,
      },
      {
        path: 'medicineInquiries',
        canActivate: [PharmacyAdminRoutes],
        component: MedicineInquiriesComponent,
      },
      {
        path: 'AllergiesOfPatient',
        canActivate: [PatientRoutes],
        component: AllergiesOfPatientComponent,
      },
    ]),
  ],

  
  

  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
    { provide: MAT_DIALOG_DATA, useValue: {} },

    AuthService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
