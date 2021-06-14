import { Component, OnInit } from '@angular/core';
import {PharmacistPatientService} from './pharmacist-patient.service'
import {Sort} from '@angular/material/sort';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { AuthService } from '../login/auth.service';

@Component({
  selector: 'app-pharmacist-patients',
  templateUrl: './pharmacist-patient.component.html',
  styleUrls: ['./pharmacist-patient.component.css']
})
export class PharmacistPatientComponent implements OnInit {

  constructor(private service : PharmacistPatientService, private router: Router, private fb: FormBuilder, private _snackBar: MatSnackBar, private  authService: AuthService) { }

  firstLogin = this.authService.getTokenData()?.firstLogin;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  patientForm! :FormGroup;
  patients:any = [];

  ngOnInit(): void {
    this.service.getPatientsPharmacist("").subscribe((data:any) => {
      this.patients = data;
      this.patients.forEach((element:any) => {
        element.date = new Date(element.date[0], element.date[1]-1, element.date[2], element.date[3]+1, element.date[4]);
      });
    });
    this.patientForm = this.fb.group(
      {
        patientSearch: ['']
      }
    );
  }

  sortData(sort: Sort){
    this.patients = this.patients.sort((a:any, b:any) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name': return this.compare(a['patient']['name'], b['patient']['name'], isAsc);
        case 'id': return this.compare(a['patient']['id'], b['patient']['id'], isAsc);
        case 'username': return this.compare(a['patient']['username'], b['patient']['username'], isAsc);
        case 'surname': return this.compare(a['patient']['surname'], b['patient']['surname'], isAsc);
        case 'email': return this.compare(a['patient']['email'], b['patient']['email'], isAsc);
        case 'address': return this.compare(a['patient']['address'], b['patient']['address'], isAsc);
        case 'city': return this.compare(a['patient']['city'], b['patient']['city'], isAsc);
        case 'country': return this.compare(a['patient']['country'], b['patient']['country'], isAsc);
        case 'phoneNumber': return this.compare(a['patient']['phoneNumber'], b['patient']['phoneNumber'], isAsc);
        case 'date': return this.compare(a['date'], b['date'], isAsc);
        default: return 0;
    }
  });
  }

  compare(a: number | string, b: number | string, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  getPatients(){
    this.service.getPatientsPharmacist(this.patientForm.get('patientSearch')?.value).subscribe((data:any) => {
      this.patients = data;
      this.patients.forEach((element:any) => {
        element.date = new Date(element.date[0], element.date[1]-1, element.date[2], element.date[3]+1, element.date[4]);
      });
    });
  }

  back(){
    this.router.navigate(['/PharmacistHomePage']);
  }

  homepage(){
    this.router.navigate(['/PharmacistHomePage']);
  }

  profile(){
    this.router.navigate(["/PharmacistProfilePageComponent"]);
  }
  
  allPatients(){
    if(!this.firstLogin){
      this.router.navigate(["/PharmacistUsersComponent"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }
  
  calendar(){
    if(!this.firstLogin){
      this.router.navigate(["/PharmacistCalendarComponent"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }
  
  absence(){
    if(!this.firstLogin){
      this.router.navigate(["/PharmacistAbsenceComponent"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }
  
  appointments(){
    if(!this.firstLogin){
      this.router.navigate(["/PharmacistAppointmentsComponent"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }
  
  reservations(){
    if(!this.firstLogin){
      this.router.navigate(["/PharmacistReservationsComponent"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }
  
  medicine(){
    if(!this.firstLogin){
      this.router.navigate(["/searchFilterMedicine"]);
    }else{
      this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
    }
  }

  logout() {
    this.authService.logout();
  }

  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

}