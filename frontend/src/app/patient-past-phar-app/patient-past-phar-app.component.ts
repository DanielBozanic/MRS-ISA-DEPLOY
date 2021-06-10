import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { PatientPastPharAppService } from './patient-past-phar-app.service';

@Component({
  selector: 'app-patient-past-phar-app',
  templateUrl: './patient-past-phar-app.component.html',
  styleUrls: ['./patient-past-phar-app.component.css']
})
export class PatientPastPharAppComponent implements OnInit {
  displayedColumns: string[] = ["DATE", "TIME", "PHARMACIST","PHARMACIST'S PHONE", "PHARMACIST'S E-MAIL", "RATING", "PRICE"];
  dataSource:any;
  elems:any;

  freeIsEmpty:boolean = false;
  notEmpty:boolean = false;

  constructor(public service:PatientPastPharAppService,  private router:Router) { 
    this.service.getAllPharApp().subscribe((data2:any)=>{
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
