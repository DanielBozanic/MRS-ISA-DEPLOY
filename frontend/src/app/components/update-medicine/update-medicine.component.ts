import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Medicine } from 'src/app/models/medicine';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';

@Component({
  selector: 'app-update-medicine',
  templateUrl: './update-medicine.component.html',
  styleUrls: ['./update-medicine.component.css'],
})
export class UpdateMedicineComponent implements OnInit {
  medicineForm: FormGroup;
  medicine!: Medicine;
  medicineId!: any;
  constructor(
    private pharmacyAdminService: PharmacyAdminService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.medicineForm = this.formBuilder.group({
      additionalComments: [],
    });
  }

  ngOnInit(): void {
    this.medicineForm = this.formBuilder.group({
      id: [],
      additionalComments: [],
    });
  }

  updateMedicine() {
    var medicine = this.medicineForm.value;
    this.medicineId = localStorage.getItem('medicineId');
    localStorage.removeItem('medicineId');
    this.pharmacyAdminService
      .updateMedicine(medicine, this.medicineId)
      .subscribe((res) => {
        this.router.navigate(['/listOfMedicine']);
      });
  }
}
