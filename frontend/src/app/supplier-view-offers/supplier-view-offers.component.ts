import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AuthService } from '../login/auth.service';
import { DateValidator } from '../supplier-write-offers/DateValidator';
import { PriceValidator } from '../supplier-write-offers/PriceValidator';
import { SupplierViewOffersService } from './supplier-view-offers.service';

@Component({
  selector: 'app-supplier-view-offers',
  templateUrl: './supplier-view-offers.component.html',
  styleUrls: ['./supplier-view-offers.component.css']
})
export class SupplierViewOffersComponent implements OnInit, AfterViewInit {
  displayedColumnsOffers: string[] = ['id', 'orderName', 'dueDateOffer', 'deliveryDate', 'price', 'offerStatus'];
  filterOptions = ['ACCEPTED', 'REJECTED', 'PENDING'];
  offersData = [];
  offersDataSource = new MatTableDataSource<any>(this.offersData);
  offerUpdateForm!: FormGroup;
  filterForm! : FormGroup;
  offers = [];
  verticalPosition: MatSnackBarVerticalPosition = "top";
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  constructor
  (
    private fb: FormBuilder,
    private supplierViewOffersService: SupplierViewOffersService,
    private _snackBar: MatSnackBar,
    private authService: AuthService
  ) { }

  @ViewChild(MatPaginator)
  paginatorOffers!: MatPaginator;

  ngOnInit(): void {
    this.getPendingOffers();
    this.filterForm = this.fb.group(
      {
        selectFilter: ['']
      }
    )
    this.offerUpdateForm = this.fb.group(
      {
        id: ['', Validators.required],
        deliveryDate: [''],
        price: ['']
      }, {validator: [DateValidator, PriceValidator]}
    );
    this.getOffersBySupplier();
  }

  ngAfterViewInit(): void {
    this.offersDataSource.paginator = this.paginatorOffers;
  }

  applyFilter(option: string) {
      const filterValue = option;
      this.offersDataSource.filter = filterValue;
  }

  clearFilter() {
    this.offersDataSource.filter = '';
    this.filterForm.get('selectFilter')?.reset();
  }

  public hasError = (controlName: string, errorName: string) =>{
    return this.offerUpdateForm.controls[controlName].hasError(errorName);
  }

  get deliveryDate() {
    return this.offerUpdateForm.get('deliveryDate');
  }

  get price() {
    return this.offerUpdateForm.get('price');
  }

  checkDate() {
    if (this.offerUpdateForm.hasError('dateInvalid')) {
      this.offerUpdateForm.get('deliveryDate')?.setErrors([{'dateInvalid': true}]);
    }
  }

  checkPrice() {
    if (this.offerUpdateForm.hasError('priceInvalid')) {
      this.offerUpdateForm.get('price')?.setErrors([{'priceInvalid': true}]);
    }
  }

  getOffersBySupplier() {
    this.supplierViewOffersService.getOffersBySupplier().subscribe(
      data => {
        this.offersData = data;
        this.offersDataSource = new MatTableDataSource<any>(this.offersData);
        this.offersDataSource.paginator = this.paginatorOffers;
        this.offersDataSource.filterPredicate = (data1, filter: string)  => {
          const accumulator = (currentTerm:any, key:any) => {
              return currentTerm + data1.offerStatus;
          };
          const dataStr = Object.keys(data1).reduce(accumulator, '').toLowerCase();
          const transformedFilter = filter.trim().toLowerCase();
          return dataStr.indexOf(transformedFilter) !== -1;
        };
      }
    );
  }

  getPendingOffers() {
    this.supplierViewOffersService.getPendingOffersBySupplier().subscribe(
      data => {
        this.offers = data;
      }
    );
  }

  updateOffer() {
    this.supplierViewOffersService.updateOffer(this.offerUpdateForm.value).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK);
        this.getOffersBySupplier();
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

