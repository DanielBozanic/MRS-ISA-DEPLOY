import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import {
  MatSnackBarVerticalPosition,
  MatSnackBar,
} from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Dermatologist } from 'src/app/models/dermatologist';
import { DummyModel } from 'src/app/models/dummy-model';
import { PharmacyAdmin } from 'src/app/models/pharmacy-admin';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';
import { Location } from '@angular/common';
import { AuthService } from 'src/app/login/auth.service';
import { Employment } from 'src/app/models/employment';
import { AddDermatologistToPharmacyPopupComponent } from '../add-dermatologist-to-pharmacy-popup/add-dermatologist-to-pharmacy-popup.component';

@Component({
  selector: 'app-list-of-dermatologists',
  templateUrl: './list-of-dermatologists.component.html',
  styleUrls: ['./list-of-dermatologists.component.css'],
})
export class ListOfDermatologistsComponent implements OnInit {
  displayedColumnsDermatologistList: string[] = [
    'name',
    'surname',
    'email',
    'phoneNumber',
    'city',
    'rating',
    'remove',
  ];
  displayedColumnsAllOtherDermatologistsList: string[] = [
    'name',
    'surname',
    'email',
    'phoneNumber',
    'city',
    'rating',
    'add',
  ];
  pharmacyAdmin!: PharmacyAdmin;
  dermatologistList!: Dermatologist[];
  dermatologistListSource = new MatTableDataSource<any>(this.dermatologistList);
  allOtherDermatologists!: Dermatologist[];
  allOtherDermatologistsSource = new MatTableDataSource<any>(
    this.allOtherDermatologists
  );

  dermatologistListBackup!: Dermatologist[];
  dermatologistSearchResult!: Dermatologist[];
  searchForm: FormGroup;
  dermatologistId!: DummyModel;
  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;
  verticalPosition: MatSnackBarVerticalPosition = 'top';
  employmentDermatologist: Employment = {
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
  paginatorDermatologist!: MatPaginator;
  @ViewChild('paginatorOtherMedicine', { static: true })
  paginatorOtherDermatologist!: MatPaginator;
  ngOnInit(): void {
    this.pharmacyAdminService.getAllDermatologists().subscribe((res) => {
      this.allOtherDermatologists = res;
      this.pharmacyAdminService.getPharmacyAdminData().subscribe((data) => {
        this.pharmacyAdmin = data;
        this.updateLists();
      });
    });
  }

  updateLists(): void {
    this.pharmacyAdminService
      .getDermatologistsFromPharmacy(this.pharmacyAdmin.pharmacy)
      .subscribe((res) => {
        this.dermatologistList = res;
        this.dermatologistListSource = new MatTableDataSource<any>(
          this.dermatologistList
        );
        this.dermatologistListSource.paginator = this.paginatorDermatologist;
        this.dermatologistListBackup = res;
        for (let der in Object.keys(this.dermatologistList)) {
          if (
            this.allOtherDermatologists.some(
              (derm) => derm.name == this.dermatologistList[der].name
            )
          ) {
            const index = this.allOtherDermatologists
              .map((e) => e.id)
              .indexOf(this.dermatologistList[der].id);
            if (index != -1) {
              this.allOtherDermatologists.splice(index, 1);
            }
          }
        }
        this.allOtherDermatologistsSource = new MatTableDataSource<any>(
          this.allOtherDermatologists
        );
        this.allOtherDermatologistsSource.paginator =
          this.paginatorOtherDermatologist;
      });
  }

  removeDermatologist(dermatologist: Dermatologist): void {
    this.pharmacyAdminService
      .deleteDermatologist(dermatologist, this.pharmacyAdmin.pharmacy)
      .subscribe((res) => {
        this.allOtherDermatologists.push(dermatologist);
        this.updateLists();
      });
  }

  searchDermatologistById(): void {
    this.dermatologistId = this.searchForm.value;
    this.pharmacyAdminService
      .searchDermatologistArrayById(this.dermatologistId.searchValue)
      .subscribe((res) => (this.dermatologistList = res));
  }

  searchDermatologistByString(): void {
    this.dermatologistId = this.searchForm.value;
    this.pharmacyAdminService
      .searchDermatologistByString(this.dermatologistId.searchValue)
      .subscribe((res) => (this.dermatologistList = res));
  }

  clearSearch(): void {
    this.dermatologistList = this.dermatologistListBackup;
    this.searchForm.reset();
  }

  addDermatologistToPharmacy(dermatologist: Dermatologist): void {
    this.employmentDermatologist.pharmacy = this.pharmacyAdmin.pharmacy;
    this.employmentDermatologist.dermatologist = dermatologist;
    const dialogRef = this.dialog.open(
      AddDermatologistToPharmacyPopupComponent,
      {
        width: '300px',
        data: {
          start: '',
          end: '',
        },
      }
    );

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
      let start = result.value.start.split(':');
      let end = result.value.end.split(':');
      this.employmentDermatologist.start = start[0];
      this.employmentDermatologist.end = end[0];
      if (result != null) {
        this.pharmacyAdminService
          .addDermatologistToPharmacy(this.employmentDermatologist)
          .subscribe((res) => {
            this.updateLists();
          });
      }
    });
  }

  public doFilter = (event: Event) => {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dermatologistListSource.filter = filterValue.trim().toLowerCase();
    this.dermatologistListSource.paginator = this.paginatorDermatologist;
  };

  public doFilter2 = (event: Event) => {
    const filterValue = (event.target as HTMLInputElement).value;
    this.allOtherDermatologistsSource.filter = filterValue.trim().toLowerCase();
    this.allOtherDermatologistsSource.paginator =
      this.paginatorOtherDermatologist;
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
