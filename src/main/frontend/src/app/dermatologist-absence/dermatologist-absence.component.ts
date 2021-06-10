import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { DermatologistAbsenceService } from './dermatologist-absence.service';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../login/auth.service';

@Component({
  selector: 'app-dermatologist-absence',
  templateUrl: './dermatologist-absence.component.html',
  styleUrls: ['./dermatologist-absence.component.css']
})

export class DermatologistAbsenceComponent implements OnInit{
    constructor(private fb: FormBuilder, private service: DermatologistAbsenceService, private _snackBar: MatSnackBar, private authService: AuthService, private router: Router) { }
  verticalPosition: MatSnackBarVerticalPosition = "top";
  firstLogin = this.authService.getTokenData()?.firstLogin;
  appointmentForm! : FormGroup;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

    ngOnInit(): void {
        this.appointmentForm = this.fb.group({
            meetingTime: ['', Validators.required],
            endingTime: ['', Validators.required],
            description: ['']
        });
    }

    public hasError = (controlName: string, errorName: string) =>{
        return this.appointmentForm.controls[controlName].hasError(errorName);
    }

    checkTime(){
        const today = new Date(Date.now());
        const start = new Date(this.appointmentForm.get("meetingTime")?.value);
        if(start < today){
            this.appointmentForm.get("meetingTime")?.setErrors([{'timeMismatch': true}])
            return null;
        }
        const end = new Date(this.appointmentForm.get("endingTime")?.value);
        if(start > end){
            this.appointmentForm.get("endingTime")?.setErrors([{'timeMismatch': true}])
            return null;
        }
        start.setHours(start.getHours() + 2);
        end.setHours(end.getHours() + 2);
        return {"start" : start.toISOString(), "end" : end.toISOString(), "description" : this.appointmentForm.get("description")?.value};
    }

    public makeAbsence(){
        let appointment = this.checkTime();
        if(appointment){
            this.service.makeAbsence(appointment).subscribe(
                response => {
                  this.openSnackBar(response, this.RESPONSE_OK);
                  this.appointmentForm.reset();
                  this.back();
                },
                error => {
                  this.openSnackBar(error.error, this.RESPONSE_ERROR);
                }
              );
        }
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