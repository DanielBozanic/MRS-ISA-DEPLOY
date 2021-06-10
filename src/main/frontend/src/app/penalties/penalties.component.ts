import { Component, OnInit } from '@angular/core';
import { PenaltiesService } from './penalties.service';

@Component({
  selector: 'app-penalties',
  templateUrl: './penalties.component.html',
  styleUrls: ['./penalties.component.css']
})
export class PenaltiesComponent implements OnInit {


  patient:any;

  constructor(public service:PenaltiesService) {
    this.getPenalties();
   }

  ngOnInit(): void {
  }

  
  getPenalties():void{
    this.service.getPenaltiesF().subscribe((data:any) =>{
      this.patient = data;
    })
  }

}
