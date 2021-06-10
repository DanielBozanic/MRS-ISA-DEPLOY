import { AfterViewInit, Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { FileInput } from 'ngx-material-file-input';
import { MedicinePrescriptionService } from './medicine-prescription.service';

@Component({
  selector: 'app-medicine-prescription',
  templateUrl: './medicine-prescription.component.html',
  styleUrls: ['./medicine-prescription.component.css']
})
export class MedicinePrescriptionComponent implements OnInit, AfterViewInit {

  constructor
  (
    private fb: FormBuilder,
    private medicinePrescriptionService: MedicinePrescriptionService,
    private _snackBar: MatSnackBar
  ) { }
  qrCodeForm!: FormGroup;
  displayedColumnsPharmacies: string[] = ['name', 'address', 'rating', 'totalPrice', 'issue'];
  displayedColumnsMedicine: string[] = ['medicineCode', 'name', 'quantity'];
  pharmacyData = [];
  pharmaciesDataSource = new MatTableDataSource<any>(this.pharmacyData);
  medicineData = [];
  medicineDataSource = new MatTableDataSource<any>(this.medicineData);
  verticalPosition: MatSnackBarVerticalPosition = "top";
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  ngOnInit(): void {
    this.qrCodeForm = this.fb.group(
      {
        qrCode: ['']
      }
    );
  }

  @ViewChildren(MatPaginator) paginator = new QueryList<MatPaginator>();

  ngAfterViewInit(): void {
    this.pharmaciesDataSource.paginator = this.paginator.toArray()[1];
    this.medicineDataSource.paginator = this.paginator.toArray()[0];
  }

  sortData(sort: Sort) {
    this.pharmaciesDataSource.data = this.pharmaciesDataSource.data.sort((a:any, b:any) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name': return this.compare(a['pharmacy']['name'], b['pharmacy']['name'], isAsc);
        case 'address': return this.compare(a['pharmacy']['address'], b['pharmacy']['address'], isAsc);
        case 'rating': return this.compare(a['pharmacy']['rating'], b['pharmacy']['rating'], isAsc);
        case 'totalPrice': return this.compare(a['total'], b['total'], isAsc);
        default: return 0;
      }
    });
  }

  compare(a: number | string, b: number | string, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  sendQrCode() {
      if (this.qrCodeForm.get('qrCode')?.value !== '') {
      const file_form: FileInput = this.qrCodeForm.get('qrCode')?.value;
      const file = file_form.files[0]; 
      const formData = new FormData();
      formData.append('qrCode', file);
      this.medicinePrescriptionService.sendQrCode(formData).subscribe(
        response => {
          this.pharmacyData = response;
          if (response != null && response.length > 0) {
            this.medicineData = response[0]['eReceiptMedicineDetails'];
          }
          if (response.length == 0) {
            this.openSnackBar("No pharmacy has the required medicine!", this.RESPONSE_ERROR);
            this.medicineData = [];
          }
          this.pharmaciesDataSource = new MatTableDataSource<any>(this.pharmacyData);
          this.pharmaciesDataSource.paginator = this.paginator.toArray()[1];
          this.medicineDataSource = new MatTableDataSource<any>(this.medicineData);
          this.medicineDataSource.paginator = this.paginator.toArray()[0];
        }
      )
    }
  }

  issueEReceipt(data: any) {
    this.medicinePrescriptionService.issueEReceipt(data).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK);
        this.pharmacyData = [];
        this.pharmaciesDataSource = new MatTableDataSource<any>(this.pharmacyData);
        this.pharmaciesDataSource.paginator = this.paginator.toArray()[1];
        this.medicineData = [];
        this.medicineDataSource = new MatTableDataSource<any>(this.medicineData);
        this.medicineDataSource.paginator = this.paginator.toArray()[0];
      },
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      }
    );
  }

  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }
}
