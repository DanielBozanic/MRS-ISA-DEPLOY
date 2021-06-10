import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Employment } from 'src/app/models/employment';
import { PharmacyAdmin } from 'src/app/models/pharmacy-admin';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';
import { Dermatologist } from 'src/app/user-complaint/user-complaint.component';

@Component({
  selector: 'app-add-dermatologist-to-pharmacy',
  templateUrl: './add-dermatologist-to-pharmacy.component.html',
  styleUrls: ['./add-dermatologist-to-pharmacy.component.css'],
})
export class AddDermatologistToPharmacyComponent implements OnInit {
  employmentForm: FormGroup;
  employment!: Employment;
  dermatologist!: Dermatologist;
  pharmacyAdmin!: PharmacyAdmin;
  constructor(
    private pharmacyAdminService: PharmacyAdminService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.employmentForm = this.formBuilder.group({
      start: [],
      end: [],
      dermatologistId: [],
      pharmacyId: [],
    });
  }

  ngOnInit(): void {
    this.employmentForm = this.formBuilder.group({
      start: [],
      end: [],
      dermatologistId: [],
      pharmacyId: [],
    });
    this.pharmacyAdminService.getPharmacyAdminData().subscribe((data) => {
      this.pharmacyAdmin = data;
    });
  }

  addDermatologistToPharmacy() {
    var employment = this.employmentForm.value;
    employment.dermatologist = JSON.parse(
      localStorage.getItem('dermatologist') || '{}'
    );
    employment.pharmacy = this.pharmacyAdmin.pharmacy;
    this.pharmacyAdminService
      .addDermatologistToPharmacy(employment)
      .subscribe((res) => {
        this.employment = res;
      });
    localStorage.removeItem('dermatologist');
    this.router.navigate(['/listOfDermatologists']);
  }
}
