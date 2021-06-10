import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MedicineNeeded } from 'src/app/models/medicineNeeded';
import { PharmacyAdminService } from 'src/app/services/pharmacy-admin.service';
import { AuthService } from 'src/app/login/auth.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-medicine-inquiries',
  templateUrl: './medicine-inquiries.component.html',
  styleUrls: ['./medicine-inquiries.component.css'],
})
export class MedicineInquiriesComponent implements OnInit {
  displayedColumnsMedicineNeeded: string[] = [
    'requested',
    'pharmacy',
    'medicine',
  ];
  medicineInquiryList!: MedicineNeeded[];
  medicineInquiryListSource = new MatTableDataSource<any>(
    this.medicineInquiryList
  );

  constructor(
    private pharmacyAdminService: PharmacyAdminService,
    private authService: AuthService,
    private location: Location
  ) {}

  @ViewChild(MatPaginator)
  paginatorMedicineInquiry!: MatPaginator;

  ngOnInit(): void {
    this.getMedicineInquiries();
  }

  getMedicineInquiries() {
    this.pharmacyAdminService.getMedicineInquiries().subscribe((data) => {
      this.medicineInquiryList = data;
      this.medicineInquiryListSource = new MatTableDataSource<any>(
        this.medicineInquiryList
      );
      this.medicineInquiryListSource.paginator = this.paginatorMedicineInquiry;
    });
  }

  back(): void {
    this.location.back();
  }

  logout() {
    this.authService.logout();
  }
}
