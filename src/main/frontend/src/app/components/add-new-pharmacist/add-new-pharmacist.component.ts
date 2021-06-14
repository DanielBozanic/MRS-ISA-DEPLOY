import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Pharmacist } from 'src/app/models/pharmacist';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';

@Component({
  selector: 'app-add-new-pharmacist',
  templateUrl: './add-new-pharmacist.component.html',
  styleUrls: ['./add-new-pharmacist.component.css'],
})
export class AddNewPharmacistComponent implements OnInit {
  pharmacistForm: FormGroup;
  pharmacist!: Pharmacist;
  constructor(
    private pharmacyAdminService: PharmacyAdminService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.pharmacistForm = this.formBuilder.group({
      name: [],
      surname: [],
      address: [],
      city: [],
      country: [],
      email: [],
      phoneNumber: [],
      username: [],
      password: [],
    });
  }

  ngOnInit(): void {
    this.pharmacistForm = this.formBuilder.group({
      id: [],
      name: [],
      surname: [],
      address: [],
      city: [],
      country: [],
      email: [],
      phoneNumber: [],
      username: [],
      password: [],
    });
  }

  addNewPharmacist() {
    var pharmacist = this.pharmacistForm.value;
    this.pharmacyAdminService.createPharmacist(pharmacist).subscribe((res) => {
      this.pharmacist = res;
    });
    this.router.navigate(['/pharmacyAdmin']);
  }
}
