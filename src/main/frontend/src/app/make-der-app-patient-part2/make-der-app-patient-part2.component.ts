import { Component, OnInit } from '@angular/core';
import { MakeDerAppPatientPart2Service } from './make-der-app-patient-part2.service';
import {MatTableDataSource} from '@angular/material/table';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-make-der-app-patient-part2',
  templateUrl: './make-der-app-patient-part2.component.html',
  styleUrls: ['./make-der-app-patient-part2.component.css']
})
export class MakeDerAppPatientPart2Component implements OnInit {

  displayedColumns: string[] = ["DATE", "TIME", "DERMATOLOG","DERMATOLOGIST PHONE", "DERMATOLOGIST E-MAIL", "RATING", "PRICE", "SCHEDULE"];
  dataSource:any;
  elems:any;

  Fpharamcy:any;

  
  freeIsEmpty:boolean = false;
  notEmpty:boolean = false;
 
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  verticalPosition: MatSnackBarVerticalPosition = "top";


  constructor(public service:MakeDerAppPatientPart2Service, private snackBar: MatSnackBar, private router:Router) { 
    this.Fpharamcy = history.state.data.pharmacyInfo;
    this.service.getAllFreeDerApp().subscribe((data2:any)=>{
      this.elems = data2.filter((el: { pharmacy: any; patient:any }) =>{
        return el.pharmacy.id == this.Fpharamcy.id &&
               el.patient == null;
      }) 
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


  schedule(row:any){
    console.log(row);
    this.service.post(row).subscribe(
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
