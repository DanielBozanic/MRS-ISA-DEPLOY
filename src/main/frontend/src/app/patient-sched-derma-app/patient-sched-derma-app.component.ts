import { Component, OnInit } from '@angular/core';
import { PatientSchedDermaAppService } from './patient-sched-derma-app.service';
import {MatTableDataSource} from '@angular/material/table';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-patient-sched-derma-app',
  templateUrl: './patient-sched-derma-app.component.html',
  styleUrls: ['./patient-sched-derma-app.component.css']
})
export class PatientSchedDermaAppComponent implements OnInit {

  displayedColumns: string[] = ["DATE", "TIME", "DERMATOLOG","DERMATOLOGIST PHONE", "DERMATOLOGIST E-MAIL", "RATING", "PRICE", "CANCEL"];
  dataSource:any;
  elems:any;

  
  freeIsEmpty:boolean = false;
  notEmpty:boolean = false;
 
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  verticalPosition: MatSnackBarVerticalPosition = "top";

  constructor(public service:PatientSchedDermaAppService, private snackBar: MatSnackBar, private router:Router) { 
    this.service.getAllDerApp().subscribe((data2:any)=>{
      this.elems = data2;
      this.dataSource = new MatTableDataSource(this.elems);
      if(this.elems.length == 0){
        this.freeIsEmpty = true;
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
    this.service.cancelApp(element).subscribe(
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
