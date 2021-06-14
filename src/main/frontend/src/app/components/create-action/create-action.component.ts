import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import {
  MatSnackBar,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { AuthService } from 'src/app/login/auth.service';
import { Action } from 'src/app/models/action';
import { PharmacyAdmin } from 'src/app/models/pharmacy-admin';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';
import { ActionPromotionPopupComponent } from '../action-promotion-popup/action-promotion-popup.component';
import { Location } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-action',
  templateUrl: './create-action.component.html',
  styleUrls: ['./create-action.component.css'],
})
export class CreateActionComponent implements OnInit {
  pharmacyAdmin!: PharmacyAdmin;
  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;
  verticalPosition: MatSnackBarVerticalPosition = 'top';
  action: Action = {
    pharmacy: null as any,
    description: null as any,
    startingDate: null as any,
    endingDate: null as any,
  };
  constructor(
    public dialog: MatDialog,
    private router: Router,

    private authService: AuthService,
    private location: Location,
    private _snackBar: MatSnackBar,
    private pharmacyAdminService: PharmacyAdminService
  ) {}

  ngOnInit(): void {
    this.pharmacyAdminService.getPharmacyAdminData().subscribe((data) => {
      this.pharmacyAdmin = data;
    });
  }

  createAction() {
    this.action.pharmacy = this.pharmacyAdmin.pharmacy;
    const dialogRef = this.dialog.open(ActionPromotionPopupComponent, {
      width: '300px',
      data: {
        startingDate: '',
        endingDate: '',
        description: '',
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
      this.action.startingDate = result.value.startingDate;
      this.action.endingDate = result.value.endingDate;
      this.action.description = result.value.description;
      if (result != null) {
        this.pharmacyAdminService.createAction(this.action).subscribe((res) => {
          this.router.navigate(['/pharmacyProfilePage']);
          this.openSnackBar('Action successfully created!', this.RESPONSE_OK);
        });
      }
    });
  }
  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, 'x', {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? 'back-green' : 'back-red',
    });
  }
  back(): void {
    this.location.back();
  }

  logout() {
    this.authService.logout();
  }
}
