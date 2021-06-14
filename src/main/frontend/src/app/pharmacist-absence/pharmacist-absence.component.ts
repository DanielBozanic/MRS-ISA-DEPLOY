import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { PharmacistAbsenceService } from './pharmacist-absence.service';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../login/auth.service';

@Component({
  selector: 'app-pharmacist-absence',
  templateUrl: './pharmacist-absence.component.html',
  styleUrls: ['./pharmacist-absence.component.css']
})

export class PharmacistAbsenceComponent implements OnInit{
    constructor(private fb: FormBuilder, private service: PharmacistAbsenceService, private _snackBar: MatSnackBar, private router: Router, private  authService: AuthService) { }
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
      
      profile(){
        this.router.navigate(["/PharmacistProfilePageComponent"]);
      }
      
      patients(){
        if(!this.firstLogin){
          this.router.navigate(["/PharmacistPatientComponent"]);
        }else{
          this.openSnackBar("You must change password before using the application.", this.RESPONSE_OK);
        }
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
    
      back(){
        this.router.navigate(['/PharmacistHomePage']);
      }

      logout() {
        this.authService.logout();
      }
}