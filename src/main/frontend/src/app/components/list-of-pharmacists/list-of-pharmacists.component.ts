import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { DummyModel } from 'src/app/models/dummy-model';
import { Pharmacist } from 'src/app/models/pharmacist';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';
import { Location } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import {
  MatSnackBarVerticalPosition,
  MatSnackBar,
} from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/login/auth.service';
import { Employment } from 'src/app/models/employment';
import { PharmacyAdmin } from 'src/app/models/pharmacy-admin';
import { AddPharmacistToPharmacyPopupComponent } from '../add-pharmacist-to-pharmacy-popup/add-pharmacist-to-pharmacy-popup.component';

@Component({
  selector: 'app-list-of-pharmacists',
  templateUrl: './list-of-pharmacists.component.html',
  styleUrls: ['./list-of-pharmacists.component.css'],
})
export class ListOfPharmacistsComponent implements OnInit {
  displayedColumnsPharmacistList: string[] = [
    'name',
    'surname',
    'email',
    'phoneNumber',
    'city',
    'rating',
    'remove',
  ];
  displayedColumnsAllOtherPharmacistsList: string[] = [
    'name',
    'surname',
    'email',
    'phoneNumber',
    'city',
    'rating',
    'add',
  ];
  pharmacyAdmin!: PharmacyAdmin;
  pharmacistList!: Pharmacist[];
  pharmacistListSource = new MatTableDataSource<any>(this.pharmacistList);
  allOtherPharmacists!: Pharmacist[];
  allOtherPharmacistsSource = new MatTableDataSource<any>(
    this.allOtherPharmacists
  );
  pharmacistListBackup!: Pharmacist[];
  pharmacistSearchResult!: Pharmacist[];
  searchForm: FormGroup;
  pharmacistId!: DummyModel;
  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;
  verticalPosition: MatSnackBarVerticalPosition = 'top';
  employmentPharmacist: Employment = {
    pharmacy: null as any,
    dermatologist: null as any,
    pharmacist: null as any,
    start: null as any,
    end: null as any,
  };
  constructor(
    private pharmacyAdminService: PharmacyAdminService,
    private router: Router,
    private location: Location,
    private _snackBar: MatSnackBar,
    public dialog: MatDialog,
    private authService: AuthService,
    private formBuilder: FormBuilder
  ) {
    this.searchForm = this.formBuilder.group({
      searchValue: [],
    });
  }
  @ViewChild('paginatorMedicine', { static: true })
  paginatorPharmacistList!: MatPaginator;
  @ViewChild('paginatorOtherMedicine', { static: true })
  paginatorOtherPharmacist!: MatPaginator;

  ngOnInit(): void {
    this.pharmacyAdminService.getUnemployedPharmacists().subscribe((res) => {
      this.allOtherPharmacists = res;
      this.pharmacyAdminService.getPharmacyAdminData().subscribe((data) => {
        this.pharmacyAdmin = data;
        this.updateLists();
      });
    });
  }
  updateLists(): void {
    this.pharmacyAdminService
      .getPharmacistsFromPharmacy(this.pharmacyAdmin.pharmacy)
      .subscribe((res) => {
        this.pharmacistList = res;
        this.pharmacistListSource = new MatTableDataSource<any>(
          this.pharmacistList
        );
        this.pharmacistListSource.paginator = this.paginatorPharmacistList;
        for (let pha in Object.keys(this.pharmacistList)) {
          if (
            this.allOtherPharmacists.some(
              (phar) => phar.name == this.pharmacistList[pha].name
            )
          ) {
            const index = this.allOtherPharmacists
              .map((e) => e.id)
              .indexOf(this.pharmacistList[pha].id);
            if (index != -1) {
              this.allOtherPharmacists.splice(index, 1);
            }
          }
        }
        this.allOtherPharmacistsSource = new MatTableDataSource<any>(
          this.allOtherPharmacists
        );
        this.allOtherPharmacistsSource.paginator =
          this.paginatorOtherPharmacist;
      });
  }

  removePharmacist(pharmacist: Pharmacist): void {
    this.pharmacyAdminService
      .deletePharmacist(pharmacist, this.pharmacyAdmin.pharmacy)
      .subscribe((res) => {
        this.allOtherPharmacists.push(pharmacist);
        this.updateLists();
      });
  }

  searchPharmacistById(): void {
    this.pharmacistId = this.searchForm.value;
    this.pharmacyAdminService
      .searchPharmacistArrayById(this.pharmacistId.searchValue)
      .subscribe((res) => (this.pharmacistList = res));
  }

  searchPharmacistByString(): void {
    this.pharmacistId = this.searchForm.value;
    this.pharmacyAdminService
      .searchPharmacistByString(this.pharmacistId.searchValue)
      .subscribe((res) => (this.pharmacistList = res));
  }

  clearSearch(): void {
    this.pharmacistList = this.pharmacistListBackup;
    this.searchForm.reset();
  }

  addPharmacistToPharmacy(pharmacist: Pharmacist): void {
    this.employmentPharmacist.pharmacy = this.pharmacyAdmin.pharmacy;
    this.employmentPharmacist.pharmacist = pharmacist;
    const dialogRef = this.dialog.open(AddPharmacistToPharmacyPopupComponent, {
      width: '300px',
      data: {
        start: '',
        end: '',
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
      let start = result.value.start.split(':');
      let end = result.value.end.split(':');
      this.employmentPharmacist.start = start[0];
      this.employmentPharmacist.end = end[0];
      if (result != null) {
        this.pharmacyAdminService
          .addPharmacistToPharmacy(this.employmentPharmacist)
          .subscribe((res) => {
            this.updateLists();
          });
      }
    });
  }

  public doFilter = (event: Event) => {
    const filterValue = (event.target as HTMLInputElement).value;
    this.pharmacistListSource.filter = filterValue.trim().toLowerCase();
    this.pharmacistListSource.paginator = this.paginatorPharmacistList;
  };

  public doFilter2 = (event: Event) => {
    const filterValue = (event.target as HTMLInputElement).value;
    this.allOtherPharmacistsSource.filter = filterValue.trim().toLowerCase();
    this.allOtherPharmacistsSource.paginator = this.paginatorOtherPharmacist;
  };

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
