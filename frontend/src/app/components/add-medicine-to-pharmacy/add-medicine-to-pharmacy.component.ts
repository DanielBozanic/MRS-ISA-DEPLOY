import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Medicine } from 'src/app/models/medicine';
import { MedicinePharmacy } from 'src/app/models/medicine-pharmacy';
import { PharmacyAdmin } from 'src/app/models/pharmacy-admin';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';

@Component({
  selector: 'app-add-medicine-to-pharmacy',
  templateUrl: './add-medicine-to-pharmacy.component.html',
  styleUrls: ['./add-medicine-to-pharmacy.component.css'],
})
export class AddMedicineToPharmacyComponent implements OnInit {
  medicinePharmacyForm: FormGroup;
  medicinePharmacy!: MedicinePharmacy;
  medicine!: Medicine;
  pharmacyAdmin!: PharmacyAdmin;
  constructor(
    private pharmacyAdminService: PharmacyAdminService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.medicinePharmacyForm = this.formBuilder.group({
      amount: [],
      cost: [],
      medicineId: [],
      pharmacyId: [],
    });
  }

  ngOnInit(): void {
    this.medicinePharmacyForm = this.formBuilder.group({
      amount: [],
      cost: [],
      medicineId: [],
      pharmacyId: [],
    });
    this.pharmacyAdminService.getPharmacyAdminData().subscribe((data) => {
      this.pharmacyAdmin = data;
    });
  }

  addMedicineToPharmacy() {
    var medicinePharmacy = this.medicinePharmacyForm.value;
    medicinePharmacy.medicine = JSON.parse(
      localStorage.getItem('medicine') || '{}'
    );
    medicinePharmacy.pharmacy = this.pharmacyAdmin.pharmacy;
    this.pharmacyAdminService
      .addMedicineToPharmacy(medicinePharmacy)
      .subscribe((res) => {
        this.medicinePharmacy = res;
      });
    localStorage.removeItem('medicine');
    this.router.navigate(['/listOfMedicine']);
  }
}
