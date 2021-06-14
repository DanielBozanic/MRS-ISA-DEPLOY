import { Component, Inject, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import {PharmacistUsersService} from './pharmacist-users.service'
import { Router } from '@angular/router';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';;
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { AuthService } from '../login/auth.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-pharmacist-users',
  templateUrl: './pharmacist-users.component.html',
  styleUrls: ['./pharmacist-users.component.css']
})
export class PharmacistUsersComponent implements OnInit {

  constructor(private service : PharmacistUsersService, private router: Router, private fb: FormBuilder, private _snackBar: MatSnackBar, private authService: AuthService, public dialog : MatDialog) { }

  @ViewChildren(MatPaginator) paginator = new QueryList<MatPaginator>();

  firstLogin = this.authService.getTokenData()?.firstLogin;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";
  patientsForm! : FormGroup;
  displayedColumnsPatients: string[] = ['name', 'surname', 'username', 'email', 'adress', 'city', 'country', 'phoneNumber'];
  patientsData:any = [];
  patientsDataSource = new MatTableDataSource<any>(this.patientsData);

  displayedColumnsAppointments: string[] = ['name', 'surname', 'startTime', 'endTime', 'start'];
  appointmentsData:any = [];
  appointmentsDataSource = new MatTableDataSource<any>(this.appointmentsData);

  ngOnInit(): void {
      this.patientsForm = this.fb.group(
      {
        nameSearch: [''],
        surnameSearch: ['']
      });
      this.getPatients("", "");
      this.getAppointments("", "");
    }

  ngAfterViewInit(): void {
    this.patientsDataSource.paginator = this.paginator.toArray()[0];
    this.appointmentsDataSource.paginator = this.paginator.toArray()[1];
  }

   getPatients(name:string, surname:string){
    this.service.getPatients(name, surname).subscribe(
      response =>{
        this.patientsData = response;
        this.patientsDataSource = new MatTableDataSource<any>(this.patientsData);
        this.patientsDataSource.paginator = this.paginator.toArray()[0];
      }
    );
   }

   getAppointments(name:string, surname:string){
    this.service.getAppointments(name, surname).subscribe(
      response =>{
        this.appointmentsData = response;
        this.appointmentsDataSource = new MatTableDataSource<any>(this.appointmentsData);
        this.appointmentsDataSource.paginator = this.paginator.toArray()[1];
      }
    );
   }

  filter(){
    this.getPatients(this.patientsForm.get("nameSearch")?.value, this.patientsForm.get("surnameSearch")?.value);
    this.getAppointments(this.patientsForm.get("nameSearch")?.value, this.patientsForm.get("surnameSearch")?.value);
  }

  start(app:any){
    let dialogRef = this.dialog.open(DialogStartPharmacistPatients, {
      data:  app
     });

     dialogRef.afterClosed().subscribe(result => {
      if(result){
          this.router.navigate(['/PharmacistAppointmentInfoComponent'], {state: {data: {appointment : app, information : {comment:"", medication:[]}}}});
      }
      else{
        dialogRef.close()
      }
    })
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

  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

  back(){
    this.router.navigate(['/PharmacistHomePage']);
  }
}

@Component({
  selector: 'start-dialog-patients',
  templateUrl: 'start-dialog-patients.html',
})
export class DialogStartPharmacistPatients {
  constructor(
      public dialogRef: MatDialogRef<DialogStartPharmacistPatients>,
      @Inject(MAT_DIALOG_DATA) public data:any
 ) {}
}