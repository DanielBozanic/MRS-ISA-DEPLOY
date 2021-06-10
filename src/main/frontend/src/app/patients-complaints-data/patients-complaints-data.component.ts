import { Component, OnInit } from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';
import { PatientsComplaintsDataService } from './patients-complaints-data.service';
import {MatTableDataSource} from '@angular/material/table';



@Component({
  selector: 'app-patients-complaints-data',
  templateUrl: './patients-complaints-data.component.html',
  styleUrls: ['./patients-complaints-data.component.css'],
  
})
export class PatientsComplaintsDataComponent implements OnInit {

  displayedColumns: string[] = ["No", "Text", "Response"];
  dataSource!: MatTableDataSource<any>;
  
  empty:boolean = false;
  notEmpty:boolean = true;
  list : any = []
  
  
  expandedElement: any | null;

  constructor(private service:PatientsComplaintsDataService) { 

    this.dataSource = new MatTableDataSource( this.list );
    this.service.getCom().subscribe((data:any)=>{
      this.list = data;
      if(this.list[0].id === 0){
        this.empty = true;
        this.notEmpty = false;
      }else{
        this.dataSource.data = this.list;
        this.empty = false;
        this.notEmpty = true;
        console.log(this.dataSource.data)}
      }
    )
      
  }

  ngOnInit(): void {
  }

}
