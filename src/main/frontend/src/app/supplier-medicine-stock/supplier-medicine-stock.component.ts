import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from '../login/auth.service';
import { SupplierWriteOffersService } from '../supplier-write-offers/supplier-write-offers.service';
import { QuantityValidator } from './QuantityValidator';
import { SupplierMedicineStockService } from './supplier-medicine-stock.service';

@Component({
  selector: 'app-supplier-medicine-stock',
  templateUrl: './supplier-medicine-stock.component.html',
  styleUrls: ['./supplier-medicine-stock.component.css']
})
export class SupplierMedicineStockComponent implements OnInit {

  constructor
  (
    private fb: FormBuilder, 
    private supplierWriteOffersService: SupplierWriteOffersService,
    private supplierMedicineStockService: SupplierMedicineStockService,
    private _snackBar: MatSnackBar,
    private authService: AuthService
  ) { }

  @ViewChild(MatPaginator)
  paginatorMedicineSupply!: MatPaginator;

  verticalPosition: MatSnackBarVerticalPosition = "top";
  displayedColumnsMedicineSupply: string[] = ['medicineCode', 'name', 'quantity'];
  medicineSupplyData = [];
  medicineSupplyDataSource = new MatTableDataSource<any>(this.medicineSupplyData);
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  updateMedicineStockForm!: FormGroup;
  listOfMedicine = [];

  ngOnInit(): void {
    this.updateMedicineStockForm = this.fb.group(
      {
        medicine: ['', Validators.required],
        quantity: ['', Validators.required]
      }, {validator: QuantityValidator}
    )
    this.getMedicineSupply();
    this.getAllMedicine();
  }

  ngAfterViewInit(): void {
    this.medicineSupplyDataSource.paginator = this.paginatorMedicineSupply;
  }

  public hasError = (controlName: string, errorName: string) =>{
    return this.updateMedicineStockForm.controls[controlName].hasError(errorName);
  }

  get quantity() {
    return this.updateMedicineStockForm.get('quantity')
  }

  checkQuantity() {
    if (this.updateMedicineStockForm.hasError('quantityInvalid')) {
      this.updateMedicineStockForm.get('quantity')?.setErrors([{'quantityInvalid': true}]);
    }
  }
  
  getMedicineSupply() {
    this.supplierWriteOffersService.getMedicineSupply().subscribe(
      response => {
        this.medicineSupplyData = response;
        this.medicineSupplyDataSource = new MatTableDataSource<any>(this.medicineSupplyData);
        this.medicineSupplyDataSource.paginator = this.paginatorMedicineSupply;
      }
    );
  }

  getAllMedicine() {
    this.supplierMedicineStockService.getAllMedicine().subscribe(
      data => {
        this.listOfMedicine = data;
      }
    );
  }

  updateMedicineStock() {
    this.supplierMedicineStockService.updateMedicineStock(this.updateMedicineStockForm.value).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK);
        this.getMedicineSupply();
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

  logout() {
    this.authService.logout();
  }
}
