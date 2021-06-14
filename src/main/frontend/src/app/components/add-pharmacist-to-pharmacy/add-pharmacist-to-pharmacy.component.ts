import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Employment } from 'src/app/models/employment';
import { PharmacyAdmin } from 'src/app/models/pharmacy-admin';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';
import { Pharmacist } from 'src/app/user-complaint/user-complaint.component';

@Component({
  selector: 'app-add-pharmacist-to-pharmacy',
  templateUrl: './add-pharmacist-to-pharmacy.component.html',
  styleUrls: ['./add-pharmacist-to-pharmacy.component.css'],
})
export class AddPharmacistToPharmacyComponent implements OnInit {
  employmentForm: FormGroup;
  employment!: Employment;
  pharmacy!: Pharmacist;
  pharmacyAdmin!: PharmacyAdmin;
  constructor(
    private pharmacyAdminService: PharmacyAdminService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.employmentForm = this.formBuilder.group({
      start: [],
      end: [],
      pharmacistId: [],
      pharmacyId: [],
    });
  }

  ngOnInit(): void {
    this.employmentForm = this.formBuilder.group({
      start: [],
      end: [],
      pharmacistId: [],
      pharmacyId: [],
    });
    this.pharmacyAdminService.getPharmacyAdminData().subscribe((data) => {
      this.pharmacyAdmin = data;
    });
  }

  addPharmacistToPharmacy() {
    var employment = this.employmentForm.value;
    employment.pharmacist = JSON.parse(
      localStorage.getItem('pharmacist') || '{}'
    );
    employment.pharmacy = this.pharmacyAdmin.pharmacy;
    this.pharmacyAdminService
      .addPharmacistToPharmacy(employment)
      .subscribe((res) => {
        this.employment = res;
      });
    localStorage.removeItem('pharmacist');
    this.router.navigate(['/listOfPharmacists']);
  }
}
