import { Component, OnInit, ViewChild } from '@angular/core';
import {PharmacistReservationsService} from './pharmacist-reservations.service'
import { Router } from '@angular/router';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { AuthService } from '../login/auth.service';

@Component({
  selector: 'app-pharmacist-reservations',
  templateUrl: './pharmacist-reservations.component.html',
  styleUrls: ['./pharmacist-reservations.component.css']
})
export class PharmacistReservationsComponent implements OnInit {

  constructor(private service : PharmacistReservationsService, private router: Router, private fb: FormBuilder, private _snackBar: MatSnackBar, private authService: AuthService) { }

  @ViewChild(MatPaginator)
  paginatorMedicineSupply!: MatPaginator;

  firstLogin = this.authService.getTokenData()?.firstLogin;
  medicinePrescribedForm! : FormGroup;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";
  displayedColumnsMedicineSupply: string[] = ['name', 'quantity'];
  displayedColumnsMedicinePrescribed: string[] = ['name', 'cost'];
  medicineSupplyData:any = [];
  medicineSupplyDataSource = new MatTableDataSource<any>(this.medicineSupplyData);
  medicinePrescribedData:any = [];
  medicinePrescribedDataSource = new MatTableDataSource<any>(this.medicinePrescribedData);
  reservation:any;

  ngOnInit(): void {
      this.medicinePrescribedForm = this.fb.group(
      {
        reservationSearch: ['']
      });
    
    }

  ngAfterViewInit(): void {
    this.medicineSupplyDataSource.paginator = this.paginatorMedicineSupply;
  }

   getReservation(){
    this.service.getReservations(this.medicinePrescribedForm.get('reservationSearch')?.value).subscribe(
      response => {
        
        if(response.length != 0){
            this.medicineSupplyData = response[0];
            this.medicineSupplyDataSource = new MatTableDataSource<any>(this.medicineSupplyData);
            this.medicineSupplyDataSource.paginator = this.paginatorMedicineSupply;

            this.medicinePrescribedData = response[1];
            this.medicinePrescribedDataSource = new MatTableDataSource<any>(this.medicinePrescribedData);
            this.reservation = response[1][0]
        }else{
            this.openSnackBar("There is no reservation with that id in your pharmacy, or the patient hasn't picked it up in time", this.RESPONSE_ERROR);
        }

      }
    )
   }

   openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

  giveOut(){
    if(this.reservation){
        this.service.giveOut(this.reservation).subscribe(
            response => {
              this.openSnackBar(response, this.RESPONSE_OK);
              this.reset();
              this.back();
            },
            error => {
              this.openSnackBar(error.error, this.RESPONSE_ERROR);
            }
          );
    }else{
        this.openSnackBar("You haven't picked a reservation, please do so.", this.RESPONSE_ERROR);
    }
  }

  reset(){
    this.medicinePrescribedForm.reset();
    this.medicineSupplyData = [];
    this.medicineSupplyDataSource = new MatTableDataSource<any>(this.medicineSupplyData);
    this.medicineSupplyDataSource.paginator = this.paginatorMedicineSupply;

    this.medicinePrescribedData = [];
    this.medicinePrescribedDataSource = new MatTableDataSource<any>(this.medicinePrescribedData);
    this.reservation = undefined;
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

  patients(){
    if(!this.firstLogin){
      this.router.navigate(["/PharmacistPatientComponent"]);
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

  back(){
    this.router.navigate(['/PharmacistHomePage']);
  }
}