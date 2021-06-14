import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import {
  MatSnackBarVerticalPosition,
  MatSnackBar,
} from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/login/auth.service';
import { PharmacyAdmin } from 'src/app/models/pharmacy-admin';
import { Promotion } from 'src/app/models/promotion';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';
import { ActionPromotionPopupComponent } from '../action-promotion-popup/action-promotion-popup.component';
import { Location } from '@angular/common';

@Component({
  selector: 'app-create-promotion',
  templateUrl: './create-promotion.component.html',
  styleUrls: ['./create-promotion.component.css'],
})
export class CreatePromotionComponent implements OnInit {
  pharmacyAdmin!: PharmacyAdmin;
  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;
  verticalPosition: MatSnackBarVerticalPosition = 'top';
  promotion: Promotion = {
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

  createPromotion() {
    this.promotion.pharmacy = this.pharmacyAdmin.pharmacy;
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
      this.promotion.startingDate = result.value.startingDate;
      this.promotion.endingDate = result.value.endingDate;
      this.promotion.description = result.value.description;
      if (result != null) {
        this.pharmacyAdminService
          .createPromotion(this.promotion)
          .subscribe((res) => {
            this.router.navigate(['/pharmacyProfilePage']);
            this.openSnackBar(
              'Promotion successfully created!',
              this.RESPONSE_OK
            );
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
