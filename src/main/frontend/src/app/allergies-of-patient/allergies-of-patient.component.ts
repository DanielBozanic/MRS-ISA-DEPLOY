import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AllergiesOfPatientService } from './allergies-of-patient.service';

@Component({
  selector: 'app-allergies-of-patient',
  templateUrl: './allergies-of-patient.component.html',
  styleUrls: ['./allergies-of-patient.component.css']
})
export class AllergiesOfPatientComponent implements OnInit {

  @ViewChild(MatPaginator)
  paginator1!: MatPaginator;


  showTableOfAllergies:boolean = true;

  displayedColumns: string[] = ["NAME", "COMPOSITION", "ADDITIONAL COMMENT"];
  
  
  elemsOfAllergies = []

  
  dataSource = new MatTableDataSource<any>(this.elemsOfAllergies);
  
  
  constructor(public service:AllergiesOfPatientService,  private router:Router) { 
    this.getAllergies();
  }


  ngOnInit(): void {
  }


  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator1;

  }


  getAllergies():void{
    this.service.getAllAllergies().subscribe((data:any) =>{
      this.elemsOfAllergies = data;
      this.dataSource = new MatTableDataSource<any>(this.elemsOfAllergies);
      this.dataSource.paginator = this.paginator1;

     
    })
  }

  



  


  

}
