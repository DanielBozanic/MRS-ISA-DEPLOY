import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { PatientSubscribedPharmaciesService } from './patient-subscribed-pharmacies.service';

@Component({
  selector: 'app-patient-subscribed-pharmacies',
  templateUrl: './patient-subscribed-pharmacies.component.html',
  styleUrls: ['./patient-subscribed-pharmacies.component.css']
})
export class PatientSubscribedPharmaciesComponent implements OnInit {

  displayedColumnsPharmacies: string[] = ['name', 'address', 'city', 
    'country', 'description', 'unsubscribe'];
  subscribedPharmaciesData = [];
  subscribedPharmaciesDataSource = new MatTableDataSource<any>(this.subscribedPharmaciesData);
  verticalPosition: MatSnackBarVerticalPosition = "top";

  constructor
  (
    private router: Router,
    private patientSubscribedPharmaciesService: PatientSubscribedPharmaciesService,
    private _snackBar: MatSnackBar, 
  ) {}

  @ViewChild(MatPaginator)
  paginatorPharmacies!: MatPaginator;

  ngOnInit(): void {
    this.getSubscribedPharmacies();
  }

  ngAfterViewInit(): void {
    this.subscribedPharmaciesDataSource.paginator = this.paginatorPharmacies;
  }

  getSubscribedPharmacies() {
    this.patientSubscribedPharmaciesService.getSubscribedPharmacies().subscribe(
      data => {
        this.subscribedPharmaciesData = data;
        this.subscribedPharmaciesDataSource = new MatTableDataSource<any>(this.subscribedPharmaciesData);
        this.subscribedPharmaciesDataSource.paginator = this.paginatorPharmacies;
      }
    );
  }

  unsubscribe(pharmacy:any) {
    this.patientSubscribedPharmaciesService.unsubscribeToPharamacy(pharmacy).subscribe(
      response => {
        this.openSnackBar(response);
        this.getSubscribedPharmacies();
      }
    );
  }

  openSnackBar(msg: string) {
    this._snackBar.open(msg, "x", {
      duration: 3000,
      verticalPosition: this.verticalPosition,
      panelClass: "back-green"
    });
  }

  back() {
    this.router.navigate(['/PatientProfile'])
  }

}