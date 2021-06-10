import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { PatiensMedicineService } from './patiens-medicine.service';

@Component({
  selector: 'app-patiens-medicines',
  templateUrl: './patiens-medicines.component.html',
  styleUrls: ['./patiens-medicines.component.css']
})
export class PatiensMedicinesComponent implements OnInit {

  displayedColumns: string[] = ['MEDICINE', 'PHARMACY', 'ADRESS', 'DATE', 'AMOUNT', 'PRICE', 'CANCEL'];
  dataSource:any;
  elems:any;

  IsEmpty:boolean = false;
  notEmpty:boolean = false;

  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  verticalPosition: MatSnackBarVerticalPosition = "top";

  
  constructor(public service:PatiensMedicineService, private snackBar: MatSnackBar, private router:Router) { 
    this.service.getAllMedicine().subscribe((data2:any)=>{
      this.elems = data2;
      this.dataSource = new MatTableDataSource(this.elems);
      if(this.elems.length == 0){
        this.IsEmpty = true;
      }else{
        this.notEmpty = true;
      }
    }) 
  }

  ngOnInit(): void {
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  


  delete(element:any){
    console.log(element);
    this.service.deleteMedicine(element).subscribe(
      response => {
        console.log(response);
        this.openSnackBar(response, this.RESPONSE_OK);
        this.router.navigate(['/UserHomePage']);
      },
      error => {
        console.log(error);
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      }
    );
  }


  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

}
