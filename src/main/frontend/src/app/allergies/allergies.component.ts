import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AllergiesService } from './allergies.service';

@Component({
  selector: 'app-allergies',
  templateUrl: './allergies.component.html',
  styleUrls: ['./allergies.component.css']
})
export class AllergiesComponent implements OnInit {


  @ViewChild(MatPaginator)
  paginator2!: MatPaginator;

  showTableOfAllergies:boolean = true;
 

  showTableOfMedicines:boolean = true;
 

  displayedColumns2: string[] = ["NAME", "COMPOSITION", "ADDITIONAL COMMENT", "ADD"]
  elemsOfMedicine = []
 

  dataSource2 = new MatTableDataSource<any>(this.elemsOfMedicine);

  
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  verticalPosition: MatSnackBarVerticalPosition = "top";
  
  constructor(public service:AllergiesService,  private snackBar: MatSnackBar, private router:Router) { 
    this.getMedicine();
  }


  ngOnInit(): void {
  }


  ngAfterViewInit(): void {
    this.dataSource2.paginator = this.paginator2;
  }




  

  getMedicine():void{
    this.service.getAllMedicines().subscribe((data:any) =>{
      this.elemsOfMedicine = data;
      this.dataSource2 = new MatTableDataSource<any>(this.elemsOfMedicine);
      this.dataSource2.paginator = this.paginator2;
    
    })
  }


 


  applyFilterMedicine(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource2.filter = filterValue.trim().toLowerCase();
    this.dataSource2.paginator = this.paginator2;
  }


  add(element:any){
    this.service.addAllergy(element).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK);
        this.getMedicine();
      },
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      })
    }

    
    openSnackBar(msg: string, responseCode: number) {
      this.snackBar.open(msg, "x", {
        duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
        verticalPosition: this.verticalPosition,
        panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
      });
    }
    
  
}
