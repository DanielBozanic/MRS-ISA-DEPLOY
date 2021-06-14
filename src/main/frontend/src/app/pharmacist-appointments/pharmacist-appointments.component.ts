import { Component, Inject, OnInit } from '@angular/core';
import {PharmacistAppointmentsService} from './pharmacist-appointments.service'
import { CalendarOptions } from '@fullcalendar/angular';
import { Router } from '@angular/router';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { AuthService } from '../login/auth.service';

@Component({
  selector: 'app-pharmacist-appointments',
  templateUrl: './pharmacist-appointments.component.html',
  styleUrls: ['./pharmacist-appointments.component.css']
})
export class PharmacistAppointmentsComponent implements OnInit {

  constructor(private service : PharmacistAppointmentsService, private router: Router, public dialog : MatDialog,  private _snackBar: MatSnackBar, private  authService: AuthService) { }

  firstLogin = this.authService.getTokenData()?.firstLogin;
  
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";
  events:any[] = [];
  current:any;

  ngOnInit(): void {
    this.service.getAppointmentsPharmacist().subscribe((data:any) => {this.events = data; this.addEvents(); console.log(this.events)});
  }

  calendarOptions: CalendarOptions = {
    initialView: 'timeGridDay',
    headerToolbar:{
        center: 'title'
    },
    events: [],
    eventClick: info => this.handleEventClicked(info)
  };

  handleEventClicked(info:any){
    let app = {};
    
    this.current = info.event
    let dialogRef = this.dialog.open(DialogStartPharmacist, {
      data:  this.current
     });

     dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.service.getAppointmentWithId(info.event.id).subscribe(data=>{{app = data};
          this.router.navigate(['/PharmacistAppointmentInfoComponent'], {state: {data: {appointment : app, information : {comment:"", medication:[]}}}});
        });
      }
      else{
        dialogRef.close()
      }
    })

  }

  addEvents(){
    this.events.forEach(event => {
      if(Array.isArray(event['start'] )){
        event['start'] = new Date(event['start'][0], event['start'][1]-1, event['start'][2], event['start'][3], event['start'][4]);
      }else{
        event['start'].setMonth(event['start'].getMonth()-1);
      }
      if(Array.isArray(event['end'] )){
        event['end'] = new Date(event['end'][0], event['end'][1]-1, event['end'][2], event['end'][3], event['end'][4]);
      }else{
        event['end'].setMonth(event['end'].getMonth()-1);
      }
    });
    this.calendarOptions.events = this.events;
 }

 back(){
  this.router.navigate(['/PharmacistHomePage']);
}

openSnackBar(msg: string, responseCode: number, duration: number) {
  this._snackBar.open(msg, "x", {
    duration: responseCode === this.RESPONSE_OK ? duration : 20000,
    verticalPosition: this.verticalPosition,
    panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
  });
}

profile(){
  this.router.navigate(["/PharmacistProfilePageComponent"]);
}

patients(){
  if(!this.firstLogin){
    this.router.navigate(["/PharmacistPatientComponent"]);
  }else{
    this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK, 20000);
  }
}

allPatients(){
  if(!this.firstLogin){
    this.router.navigate(["/PharmacistUsersComponent"]);
  }else{
    this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK, 20000);
  }
}

calendar(){
  if(!this.firstLogin){
    this.router.navigate(["/PharmacistCalendarComponent"]);
  }else{
    this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK, 20000);
  }
}

absence(){
  if(!this.firstLogin){
    this.router.navigate(["/PharmacistAbsenceComponent"]);
  }else{
    this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK, 20000);
  }
}

appointments(){
  if(!this.firstLogin){
    this.router.navigate(["/PharmacistAppointmentsComponent"]);
  }else{
    this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK, 20000);
  }
}

reservations(){
  if(!this.firstLogin){
    this.router.navigate(["/PharmacistReservationsComponent"]);
  }else{
    this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK, 20000);
  }
}

medicine(){
  if(!this.firstLogin){
    this.router.navigate(["/searchFilterMedicine"]);
  }else{
    this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK, 20000);
  }
}

logout() {
  this.authService.logout();
}

}

@Component({
  selector: 'start-dialog',
  templateUrl: 'start-dialog.html',
})
export class DialogStartPharmacist {
  constructor(
      public dialogRef: MatDialogRef<DialogStartPharmacist>,
      @Inject(MAT_DIALOG_DATA) public data:any
 ) {}
}