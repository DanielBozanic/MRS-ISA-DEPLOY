import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Dermatologist } from 'src/app/models/dermatologist';
import { DummyModel } from 'src/app/models/dummy-model';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';

@Component({
  selector: 'app-list-of-dermatologists',
  templateUrl: './list-of-dermatologists.component.html',
  styleUrls: ['./list-of-dermatologists.component.css'],
})
export class ListOfDermatologistsComponent implements OnInit {
  dermatologistList!: Dermatologist[];
  dermatologistListBackup!: Dermatologist[];
  dermatologistSearchResult!: Dermatologist[];
  searchForm: FormGroup;
  dermatologistId!: DummyModel;

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
      .getAllDermatologists()
      .subscribe(
        (res) => (
          (this.dermatologistList = res), (this.dermatologistListBackup = res)
        )
      );
  }

  deleteDermatologist(dermatologist: Dermatologist): void {
    this.pharmacyAdminService
      .deleteDermatologist(dermatologist)
      .subscribe((res) => {
        this.pharmacyAdminService
          .getAllDermatologists()
          .subscribe((res) => (this.dermatologistList = res));
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
    localStorage.setItem('dermatologist', JSON.stringify(dermatologist));
    this.router.navigate(['addDermatologistToPharmacy']);
  }
}
