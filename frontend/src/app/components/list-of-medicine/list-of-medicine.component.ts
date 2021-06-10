import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { DummyModel } from 'src/app/models/dummy-model';
import { Medicine } from 'src/app/models/medicine';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';

@Component({
  selector: 'app-list-of-medicine',
  templateUrl: './list-of-medicine.component.html',
  styleUrls: ['./list-of-medicine.component.css'],
})
export class ListOfMedicineComponent implements OnInit {
  medicineList!: Medicine[];
  medicineListBackup!: Medicine[];
  medicineSearchResult!: Medicine[];
  searchForm: FormGroup;
  medicineId!: DummyModel;
  constructor(
    private pharmacyAdminService: PharmacyAdminService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.searchForm = this.formBuilder.group({
      searchValue: [],
    });
  }

  ngOnInit(): void {
    this.pharmacyAdminService
      .getAllMedicine()
      .subscribe(
        (res) => ((this.medicineList = res), (this.medicineListBackup = res))
      );
  }

  deleteMedicine(medicine: Medicine): void {
    this.pharmacyAdminService
      .deleteMedicine(medicine)
      .subscribe((res) =>
        this.pharmacyAdminService
          .getAllMedicine()
          .subscribe((res) => (this.medicineList = res))
      );
  }
  updateMedicine(medicine: Medicine): void {
    localStorage.setItem('medicineId', medicine.id);
    this.router.navigate(['updateMedicine']);
  }

  addMedicineToPharmacy(medicine: Medicine): void {
    localStorage.setItem('medicine', JSON.stringify(medicine));
    this.router.navigate(['addMedicineToPharmacy']);
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
}
