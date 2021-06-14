import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import {
  MatSnackBar,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/login/auth.service';
import { DummyModel } from 'src/app/models/dummy-model';
import { Medicine } from 'src/app/models/medicine';
import { PharmacyAdmin } from 'src/app/models/pharmacy-admin';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';
import { Location } from '@angular/common';
import { MedicinePharmacy } from 'src/app/models/medicine-pharmacy';
import { AddMedicineToPharmacyPopupComponent } from '../add-medicine-to-pharmacy-popup/add-medicine-to-pharmacy-popup.component';
import { MatDialog } from '@angular/material/dialog';
import { UpdateMedicineCommentPopupComponent } from '../update-medicine-comment-popup/update-medicine-comment-popup.component';

@Component({
  selector: 'app-list-of-medicine',
  templateUrl: './list-of-medicine.component.html',
  styleUrls: ['./list-of-medicine.component.css'],
})
export class ListOfMedicineComponent implements OnInit {
  displayedColumnsPharmacyMedicineList: string[] = [
    'name',
    'composition',
    'form',
    'manufacturer',
    'medicineCode',
    'medicineType',
    'additionalComments',
    'prescription',
    'id',
    'edit',
    'remove',
  ];
  displayedColumnsOtherMedicineList: string[] = [
    'name',
    'composition',
    'form',
    'manufacturer',
    'medicineCode',
    'medicineType',
    'additionalComments',
    'prescription',
    'id',
    'add',
  ];
  pharmacyAdmin!: PharmacyAdmin;
  allOtherMedicine!: Medicine[];
  allOtherMedicineList!: Medicine[];
  allOtherMedicineSource = new MatTableDataSource<any>(this.allOtherMedicine);
  medicineList!: Medicine[];
  medicineListSource = new MatTableDataSource<any>(this.medicineList);
  medicineListBackup!: Medicine[];
  medicineSearchResult!: Medicine[];
  searchForm: FormGroup;
  medicineId!: DummyModel;
  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;
  verticalPosition: MatSnackBarVerticalPosition = 'top';
  medicinePharmacy: MedicinePharmacy = {
    pharmacy: null as any,
    medicine: null as any,
    cost: null as any,
    amount: null as any,
  };
  constructor(
    private pharmacyAdminService: PharmacyAdminService,
    private router: Router,
    private authService: AuthService,
    private location: Location,
    private _snackBar: MatSnackBar,
    public dialog: MatDialog,

    private formBuilder: FormBuilder
  ) {
    this.searchForm = this.formBuilder.group({
      searchValue: [],
    });
  }
  @ViewChild('paginatorMedicine', { static: true })
  paginatorMedicine!: MatPaginator;
  @ViewChild('paginatorOtherMedicine', { static: true })
  paginatorOtherMedicine!: MatPaginator;

  ngOnInit(): void {
    this.pharmacyAdminService.getAllMedicine().subscribe((res) => {
      this.allOtherMedicine = res;
      this.pharmacyAdminService.getPharmacyAdminData().subscribe((data) => {
        this.pharmacyAdmin = data;
        this.updateLists();
      });
    });
  }

  updateLists(): void {
    this.pharmacyAdminService
      .getMedicineFromPharmacy(this.pharmacyAdmin.pharmacy)
      .subscribe((res) => {
        this.medicineList = res;
        this.medicineListSource = new MatTableDataSource<any>(
          this.medicineList
        );
        this.medicineListSource.paginator = this.paginatorMedicine;
        this.medicineListBackup = res;
        for (let med in Object.keys(this.medicineList)) {
          if (
            this.allOtherMedicine.some(
              (medi) => medi.name == this.medicineList[med].name
            )
          ) {
            const index = this.allOtherMedicine
              .map((e) => e.id)
              .indexOf(this.medicineList[med].id);
            if (index != -1) {
              this.allOtherMedicine.splice(index, 1);
            }
          }
        }
        //this.allOtherMedicineList = this.allOtherMedicine;
        this.allOtherMedicineSource = new MatTableDataSource<any>(
          this.allOtherMedicine
        );
        this.allOtherMedicineSource.paginator = this.paginatorOtherMedicine;
      });
  }

  deleteMedicine(medicine: Medicine): void {
    this.pharmacyAdminService
      .deleteMedicine(medicine, this.pharmacyAdmin.pharmacy)
      .subscribe((res) => {
        this.allOtherMedicine.push(medicine);
        this.updateLists();
      });
  }
  updateMedicine(medicine: Medicine): void {
    const dialogRef = this.dialog.open(UpdateMedicineCommentPopupComponent, {
      width: '300px',
      data: {
        additionalComments: medicine.additionalComments,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
      medicine.additionalComments = result.value.additionalComments;
      if (result != null) {
        this.pharmacyAdminService
          .updateMedicine(medicine, medicine.id)
          .subscribe((res) => {
            this.updateLists();
          });
      }
    });
  }

  addMedicineToPharmacy(medicine: Medicine): void {
    this.medicinePharmacy.pharmacy = this.pharmacyAdmin.pharmacy;
    this.medicinePharmacy.medicine = medicine;
    const dialogRef = this.dialog.open(AddMedicineToPharmacyPopupComponent, {
      width: '300px',
      data: {
        startingDate: '',
        endingDate: '',
        description: '',
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
      this.medicinePharmacy.cost = result.value.cost;
      this.medicinePharmacy.amount = result.value.amount;
      if (result != null) {
        this.pharmacyAdminService
          .addMedicineToPharmacy(this.medicinePharmacy)
          .subscribe((res) => {
            this.updateLists();
          });
      }
    });
  }

  searchMedicineById(): void {
    this.medicineId = this.searchForm.value;
    this.pharmacyAdminService
      .searchMedicineArrayById(this.medicineId.searchValue)
      .subscribe((res) => (this.medicineList = res));
  }

  searchMedicineByString(): void {
    this.medicineId = this.searchForm.value;
    this.pharmacyAdminService
      .searchMedicineByString(this.medicineId.searchValue)
      .subscribe((res) => (this.medicineList = res));
  }

  clearSearch(): void {
    this.medicineList = this.medicineListBackup;
    this.searchForm.reset();
  }

  public doFilter = (event: Event) => {
    const filterValue = (event.target as HTMLInputElement).value;
    this.medicineListSource.filter = filterValue.trim().toLowerCase();
    this.medicineListSource.paginator = this.paginatorMedicine;
  };

  public doFilter2 = (event: Event) => {
    const filterValue = (event.target as HTMLInputElement).value;
    this.allOtherMedicineSource.filter = filterValue.trim().toLowerCase();
    this.allOtherMedicineSource.paginator = this.paginatorOtherMedicine;
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
