import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { PatientPastDermaAppService } from './patient-past-derma-app.service';

@Component({
  selector: 'app-patient-past-derma-app',
  templateUrl: './patient-past-derma-app.component.html',
  styleUrls: ['./patient-past-derma-app.component.css']
})
export class PatientPastDermaAppComponent implements OnInit {

  displayedColumns: string[] = ["DATE", "TIME", "DERMATOLOG","DERMATOLOGIST PHONE", "DERMATOLOGIST E-MAIL", "RATING", "PRICE"];
  dataSource:any;
  elems:any;

  
  freeIsEmpty:boolean = false;
  notEmpty:boolean = false;


  constructor(public service:PatientPastDermaAppService, private router:Router) {
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

}
