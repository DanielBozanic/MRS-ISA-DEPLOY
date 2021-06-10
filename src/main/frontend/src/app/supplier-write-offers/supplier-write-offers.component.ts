import { AfterViewInit, Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from '../login/auth.service';
import { DateValidator } from './DateValidator';
import { PriceValidator } from './PriceValidator';
import { SupplierWriteOffersService } from './supplier-write-offers.service';

@Component({
  selector: 'app-supplier-write-offers',
  templateUrl: './supplier-write-offers.component.html',
  styleUrls: ['./supplier-write-offers.component.css']
})
export class SupplierWriteOffersComponent implements OnInit, AfterViewInit  {

  constructor
  (
    private fb: FormBuilder, 
    private supplierWriteOffersService: SupplierWriteOffersService,
    private _snackBar: MatSnackBar,
    private authService: AuthService,
  ) { }

  @ViewChildren(MatPaginator) paginator = new QueryList<MatPaginator>();

  verticalPosition: MatSnackBarVerticalPosition = "top";
  displayedColumnsMedicineSupply: string[] = ['medicineCode', 'name', 'quantity'];
  displayedColumnsOrders: string[] = ['id', 'orderName', 'medicineCode', 'name', 'quantity', 'dueDateOffer'];
  medicineSupplyData = [];
  orderData = [];
  orders = [];
  medicineSupplyDataSource = new MatTableDataSource<any>(this.medicineSupplyData);
  orderDataSource = new MatTableDataSource<any>(this.orderData);
  offerForm!: FormGroup;
  purchaseOrdersForm!: FormGroup;
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  ngOnInit(): void {
    this.offerForm = this.fb.group(
      {
        purchaseOrder: ['', Validators.required],
        deliveryDate: ['', Validators.required],
        price: [0, Validators.required]
      }, {validator: [DateValidator, PriceValidator]}
    );
    this.supplierWriteOffersService.getOrders().subscribe(
      data => {
        this.orders = data
      }
    )
    this.getMedicineSupply();
  }

  ngAfterViewInit(): void {
    this.medicineSupplyDataSource.paginator = this.paginator.toArray()[0];
    this.orderDataSource.paginator = this.paginator.toArray()[1]
  }

  public hasError = (controlName: string, errorName: string) =>{
    return this.offerForm.controls[controlName].hasError(errorName);
  }

  get deliveryDate() {
    return this.offerForm.get('deliveryDate');
  }

  get price() {
    return this.offerForm.get('price');
  }

  checkDate() {
    if (this.offerForm.hasError('dateInvalid')) {
      this.offerForm.get('deliveryDate')?.setErrors([{'dateInvalid': true}]);
    }
  }

  checkPrice() {
    if (this.offerForm.hasError('priceInvalid')) {
      this.offerForm.get('price')?.setErrors([{'priceInvalid': true}]);
    }
  }

  getMedicineSupply() {
    this.supplierWriteOffersService.getMedicineSupply().subscribe(
      response => {
        this.medicineSupplyData = response;
        this.medicineSupplyDataSource = new MatTableDataSource<any>(this.medicineSupplyData);
        this.medicineSupplyDataSource.paginator = this.paginator.toArray()[0];
      }
    )
  }

  getPurchaseOrdersMedicine() {
    this.supplierWriteOffersService.getPurchaseOrdersMedicine(this.offerForm.get('purchaseOrder')?.value['id']).
      subscribe(
        data => {
          this.orderData = data;
          this.orderDataSource = new MatTableDataSource<any>(this.orderData);
          this.orderDataSource.paginator = this.paginator.toArray()[1]
        }
      )
  }

  writeOffer() {
    this.supplierWriteOffersService.writeOffer(this.offerForm.value).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK);
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
