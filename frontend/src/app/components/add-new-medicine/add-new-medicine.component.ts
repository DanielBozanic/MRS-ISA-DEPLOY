import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Medicine } from 'src/app/models/medicine';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';

@Component({
  selector: 'app-add-new-medicine',
  templateUrl: './add-new-medicine.component.html',
  styleUrls: ['./add-new-medicine.component.css'],
})
export class AddNewMedicineComponent implements OnInit {
  medicineForm: FormGroup;
  medicine!: Medicine;
  constructor(
    private pharmacyAdminService: PharmacyAdminService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.medicineForm = this.formBuilder.group({
      name: [],
    });
  }

  ngOnInit(): void {
    this.medicineForm = this.formBuilder.group({
      id: [],
      name: [],
    });
  }

  addNewMedicine() {
    var medicine = this.medicineForm.value;
    this.pharmacyAdminService.createMedicine(medicine).subscribe((res) => {
      this.medicine = res;
    });
    this.router.navigate(['/pharmacyAdmin']);
  }
}
