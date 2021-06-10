import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../login/auth.service';

@Component({
    selector: 'app-dermatologist-home-page',
    templateUrl: './dermatologist-home-page.component.html',
    styleUrls: ['./dermatologist-home-page.component.css']
  })
  export class DermatologistHomePageComponent implements OnInit {
  
    constructor(private authService: AuthService, private _snackBar: MatSnackBar, private router: Router) { }
    firstLogin = this.authService.getTokenData()?.firstLogin;
    RESPONSE_OK : number = 0;
    RESPONSE_ERROR : number = -1;
    verticalPosition: MatSnackBarVerticalPosition = "top";
  
    ngOnInit(): void {
      if (this.firstLogin) {
        this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK, 20000);
      }
    }

    logout() {
      this.authService.logout();
    }

    openSnackBar(msg: string, responseCode: number, duration: number) {
      this._snackBar.open(msg, "x", {
        duration: responseCode === this.RESPONSE_OK ? duration : 20000,
        verticalPosition: this.verticalPosition,
        panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
      });
    }

    profile(){
      this.router.navigate(["/DermatologistProfilePageComponent"]);
    }

    patients(){
      if(!this.firstLogin){
        this.router.navigate(["/DermatologistPatientComponent"]);
      }else{
        this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK, 20000);
      }
    }

    allPatients(){
      if(!this.firstLogin){
        this.router.navigate(["/DermatologistUsersComponent"]);
      }else{
        this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK, 20000);
      }
    }

    calendar(){
      if(!this.firstLogin){
        this.router.navigate(["/DermatologistCalendarComponent"]);
      }else{
        this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK, 20000);
      }
    }

    absence(){
      if(!this.firstLogin){
        this.router.navigate(["/DermatologistAbsenceComponent"]);
      }else{
        this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK, 20000);
      }
    }

    appointments(){
      if(!this.firstLogin){
        this.router.navigate(["/DermatologistAppointmentsComponent"]);
      }else{
        this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK, 20000);
      }
    }

    reservations(){
      if(!this.firstLogin){
        this.router.navigate(["/DermatologistReservationsComponent"]);
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
  
  }