import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { PatientsEreceptsService } from './patients-erecepts.service';

@Component({
  selector: 'app-patients-erecepts',
  templateUrl: './patients-erecepts.component.html',
  styleUrls: ['./patients-erecepts.component.css']
})
export class PatientsEreceptsComponent implements OnInit {

  @ViewChild(MatPaginator)
  paginator1!: MatPaginator;


  showTableOfERecept:boolean = true;
  showEmpty:boolean = false;

  displayedColumns: string[] = ["NAME", "DATE", "PHARMACY", "QUANTITY", "TOTAL"];
  
  
  elemsOfAllergies = []

  
  dataSource = new MatTableDataSource<any>(this.elemsOfAllergies);
  
  
  constructor(public service:PatientsEreceptsService,  private router:Router) { 
    this.getAllergies();
  }


  ngOnInit(): void {
  }


  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator1;

  }


  getAllergies():void{
    this.service.getAllrecepts().subscribe((data:any) =>{
      this.elemsOfAllergies = data;

      if(data.length == 0){
        this.showEmpty = true;
        this.showTableOfERecept = false;
      }
      this.dataSource = new MatTableDataSource<any>(this.elemsOfAllergies);
      this.dataSource.paginator = this.paginator1;

     
    })

  }

}
