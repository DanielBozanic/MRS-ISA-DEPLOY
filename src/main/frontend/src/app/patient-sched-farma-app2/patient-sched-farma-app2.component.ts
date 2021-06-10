import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { PatientSchedFarmaApp2Service } from './patient-sched-farma-app2.service';


@Component({
  selector: 'app-patient-sched-farma-app2',
  templateUrl: './patient-sched-farma-app2.component.html',
  styleUrls: ['./patient-sched-farma-app2.component.css']
})
export class PatientSchedFarmaApp2Component implements OnInit {

  displayedColumns: string[] = ['name', 'address', 'city', 'country', 'rate', 'homepage'];
  dataSource!: MatTableDataSource<any>;


  displayedColumns2: string[] = ["identity", "phoneNumber", "email", "rating", "schedule"]
  dataSource2!: MatTableDataSource<any>;

  empty:boolean = false;
  notEmpty:boolean = false;

  allAppoinments:any = [];
  appointmets:any = [];
  
  pharmacies:any = [];
  pharmaceuts:any
  final:any;


  employment:any;
  date:any;
  canceled:any;
  checkCanceled:boolean = false;


  constructor(
    public service: PatientSchedFarmaApp2Service,
    private snackBar: MatSnackBar,
    private router:Router
  ) { 
    this.prepareArrays();
  }


  ngOnInit(): void {
    this.dataSource = new MatTableDataSource( this.pharmacies );
    this.dataSource2 = new MatTableDataSource( this.pharmaceuts );
  }

 

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;






ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

    this.dataSource2.paginator = this.paginator;
    this.dataSource2.sort = this.sort;
  }




  prepareArrays(){
    this.service.getPharmacistAppoinment().subscribe((data2:any) => {
      this.employment = history.state.data.employments;
      this.date = history.state.data.date;
      this.canceled = history.state.data.canceled;
      
      let k = 0;
      
      
      for(k= 0; k<data2.length; k++){
        if(data2[k].start[0] == this.date.getFullYear()){
          if(data2[k].start[1] == this.date.getMonth()+1){
            if(data2[k].start[2] == this.date.getDate()){
              if(this.date.getHours() + 1 <= data2[k].start[3] && this.date.getMinutes() <= data2[k].start[4] || this.date.getHours() >= data2[k].end[3] && this.date.getMinutes() >= data2[k].end[4]){
              }else{
                this.appointmets.push(data2[k]); //ovi ljudi su zauzeti
              }
            }
          }
        }
      }


      console.log(this.canceled.length);

      let c;
      for(c = 0; c<this.canceled.length; c++){
        if(this.date.getFullYear() == this.canceled[c].start[0] && this.date.getMonth()+1 == this.canceled[c].start[1]
          && this.date.getDate() == this.canceled[c].start[2] && this.date.getHours() == this.canceled[c].start[3]
          && this.date.getMinutes() == this.canceled[c].start[4]){
            console.log("usla")
            this.checkCanceled = true;
            break;
        }
      }
      

      let c2;
      if(this.checkCanceled){
        for(c2 = 0; c2<this.employment.length; c2++){
          if(this.isCanceled(this.employment[c2])){
            this.employment.splice(c2, 1);
          }
        }
      }
      
      


      let i = 0;
      for(i = 0; i<this.employment.length; i++){
        if(this.isit(this.employment[i])){
          this.employment.splice(i, 1);
        }
      }


      


      if(this.employment.length != 0){
        let i = 0;
        for(; i < this.employment.length; i++){
          if(!(this.checkInList(this.employment[i].pharmacy))){
            this.pharmacies.push(this.employment[i].pharmacy);
          }
        }
      }



      this.dataSource.data = this.pharmacies;
      if(!(this.pharmacies.length == 0)){
        this.notEmpty = true;
      }else{
        this.empty = true;
      }

    })
  }



  checkInList(pharmacy:any){
    if(this.pharmacies.length == 0){
      return false;
    }else{
      let i =0;
      for(; i<this.pharmacies.length; i++){
        if(this.pharmacies[i].id == pharmacy.id){
          return true;
        }
      }
    }
    return false;
  }




  isit(e:any){
    let j= 0;
    for(j = 0; j<this.appointmets.length; j++){
      if(e.pharmacist.id == this.appointmets[j].pharmacist.id){
        return true;
      }
    }
    return false;
  }



  isCanceled(e:any){
    let c;
    for(c = 0; c<this.canceled.length; c++){
      if(e.pharmacist.id == this.canceled[c].pharmacist.id){
        return true;
      }
    }
    return false;
  }

  
  showPharmaceuts:boolean = false;

  checkhere(row:any){
    this.notEmpty = false;
    this.pharmaceuts = this.employment.filter((el: { pharmacy: any}) =>{
      return el.pharmacy.id == row.id;
    }) 
    console.log(this.pharmaceuts);
    this.dataSource2.data = this.pharmaceuts;
    this.showPharmaceuts = true;
  }


  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  verticalPosition: MatSnackBarVerticalPosition = "top";

  schedulePhar(row:any){
    this.date = history.state.data.date;
    let newAppointment = {"start" : [this.date.getFullYear(), this.date.getMonth()+1, this.date.getDate(), this.date.getHours(), this.date.getMinutes()], "end" : [this.date.getFullYear(), this.date.getMonth()+1, this.date.getDate(), this.date.getHours()+1, this.date.getMinutes()], "patient" : null, "pharmacist" : row.pharmacist, 
        "pharmacy" : row.pharmacy, "price" : null, "discount": 0, "done": false};
        this.service.addNewAppointment(newAppointment).subscribe(
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




  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
 

  applyFilter2(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource2.filter = filterValue.trim().toLowerCase();

    if (this.dataSource2.paginator) {
      this.dataSource2.paginator.firstPage();
    }
  }



  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

}


