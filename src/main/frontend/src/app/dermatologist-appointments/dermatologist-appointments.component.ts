import { Component, Inject, OnInit } from '@angular/core';
import {DermatologistAppointmentsService} from './dermatologist-appointments.service'
import { CalendarOptions } from '@fullcalendar/angular';
import { Router } from '@angular/router';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { AuthService } from '../login/auth.service';

@Component({
  selector: 'app-dermatologist-appointments',
  templateUrl: './dermatologist-appointments.component.html',
  styleUrls: ['./dermatologist-appointments.component.css']
})
export class DermatologistAppointmentsComponent implements OnInit {

  constructor(private service : DermatologistAppointmentsService, private router: Router, public dialog : MatDialog, private _snackBar: MatSnackBar, private authService: AuthService) { }
  verticalPosition: MatSnackBarVerticalPosition = "top";
  firstLogin = this.authService.getTokenData()?.firstLogin;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;


  events:any[] = [];
  current:any;

  ngOnInit(): void {
    this.service.getAppointmentsPharmacist().subscribe((data:any) => {this.events = data; this.addEvents(); console.log(this.events)});
  }

  calendarOptions: CalendarOptions = {
    initialView: 'timeGridDay',
    headerToolbar:{
        center: 'title',
        right: 'timeGridDay'
    },
    events: [],
    eventClick: info => this.handleEventClicked(info)
  };

  handleEventClicked(info:any){
    let app = {};
    
    this.current = info.event
    let dialogRef = this.dialog.open(DialogStartDermatologist, {
      data:  this.current
     });

     dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.service.getAppointmentWithId(info.event.id).subscribe(data=>{{app = data};
          this.router.navigate(['/DermatologistAppointmentInfoComponent'], {state: {data: {appointment : app, information : {comment:"", medication:[]}}}});
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

openSnackBar(msg: string, responseCode: number) {
  this._snackBar.open(msg, "x", {
    duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
    verticalPosition: this.verticalPosition,
    panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
  });
}

logout() {
  this.authService.logout();
}

profile(){
  this.router.navigate(["/DermatologistProfilePageComponent"]);
}

patients(){
  if(!this.firstLogin){
    this.router.navigate(["/DermatologistPatientComponent"]);
  }else{
    this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
  }
}

allPatients(){
  if(!this.firstLogin){
    this.router.navigate(["/DermatologistUsersComponent"]);
  }else{
    this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
  }
}

calendar(){
  if(!this.firstLogin){
    this.router.navigate(["/DermatologistCalendarComponent"]);
  }else{
    this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
  }
}

absence(){
  if(!this.firstLogin){
    this.router.navigate(["/DermatologistAbsenceComponent"]);
  }else{
    this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
  }
}

appointments(){
  if(!this.firstLogin){
    this.router.navigate(["/DermatologistAppointmentsComponent"]);
  }else{
    this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
  }
}

reservations(){
  if(!this.firstLogin){
    this.router.navigate(["/DermatologistReservationsComponent"]);
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

back(){
  this.router.navigate(['/DermatologistHomePage']);
}

}

@Component({
  selector: 'start-dialog',
  templateUrl: 'start-dialog.html',
})
export class DialogStartDermatologist {
  constructor(
      public dialogRef: MatDialogRef<DialogStartDermatologist>,
      @Inject(MAT_DIALOG_DATA) public data:any
 ) {}
}