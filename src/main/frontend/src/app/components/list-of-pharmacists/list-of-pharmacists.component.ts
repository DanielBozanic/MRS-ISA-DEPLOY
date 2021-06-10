import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { DummyModel } from 'src/app/models/dummy-model';
import { Pharmacist } from 'src/app/models/pharmacist';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';

@Component({
  selector: 'app-list-of-pharmacists',
  templateUrl: './list-of-pharmacists.component.html',
  styleUrls: ['./list-of-pharmacists.component.css'],
})
export class ListOfPharmacistsComponent implements OnInit {
  pharmacistList!: Pharmacist[];
  pharmacistListBackup!: Pharmacist[];
  pharmacistSearchResult!: Pharmacist[];
  searchForm: FormGroup;
  pharmacistId!: DummyModel;
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
      .getAllPharmacists()
      .subscribe(
        (res) => (
          (this.pharmacistList = res), (this.pharmacistListBackup = res)
        )
      );
  }

  deletePharmacist(pharmacist: Pharmacist): void {
    this.pharmacyAdminService.deletePharmacist(pharmacist).subscribe((res) => {
      this.pharmacyAdminService
        .getAllPharmacists()
        .subscribe((res) => (this.pharmacistList = res));
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
    localStorage.setItem('pharmacist', JSON.stringify(pharmacist));
    this.router.navigate(['addPharmacistToPharmacy']);
  }
}
