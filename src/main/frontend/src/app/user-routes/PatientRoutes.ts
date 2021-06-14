import { Injectable } from "@angular/core";
import { CanActivate } from "@angular/router";
import { AuthService } from "../login/auth.service";

@Injectable({
    providedIn: 'root'
  })
  export class PatientRoutes implements CanActivate {
  
    constructor(private authService: AuthService) { }
  
    canActivate() {
      if (this.authService.getTokenData()?.role === 'ROLE_PATIENT') {
        return true;
      }
      return false;
    }
  }