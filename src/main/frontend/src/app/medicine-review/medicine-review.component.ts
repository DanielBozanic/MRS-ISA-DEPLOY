import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import jspdf from 'jspdf';
import { AuthService } from '../login/auth.service';
import { MedicineReviewService } from './medicine-review.service';

@Component({
  selector: 'app-medicine-review',
  templateUrl: './medicine-review.component.html',
  styleUrls: ['./medicine-review.component.css']
})
export class MedicineReviewComponent implements OnInit {

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  showTable:boolean = true;
  showCard:boolean = false;
  empty:boolean = false;
  displayedColumns: string[] = ["NAME", "COMPOSITION", "ADDITIONAL COMMENT", "RATING", "RATE"];
  elems = []
  dataSource = new MatTableDataSource<any>(this.elems);
  
  
  chosenMedicine:any;
  
  chosenStars!:string;
  grade: number = 0;
  stars: string[] = ['★', '★★', '★★★', '★★★★', '★★★★★'];
  

  
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  verticalPosition: MatSnackBarVerticalPosition = "top";
  
  constructor(public service:MedicineReviewService, private snackBar: MatSnackBar, private router:Router) { 
    this.getMedicine(); 
  }


  ngOnInit(): void {
  }


  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }


  getMedicine():void{
    this.service.getAllMedicines().subscribe((data2:any)=>{ 
      this.elems = data2;
      this.dataSource = new MatTableDataSource<any>(this.elems);
      this.dataSource.paginator = this.paginator;
      }) 

      if(this.elems.length == 0){
        this.empty = true;
        this.showTable = false;
      }
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    this.dataSource.paginator = this.paginator;
    
  }



  rate(element:any){
    this.showCard = true;
    this.chosenMedicine = element;
  }

  
  sendRating(){
    console.log(this.chosenMedicine);
    console.log(this.chosenStars);

    if(this.chosenStars == "★"){
      this.grade = 1;
    }else if(this.chosenStars == "★★"){
      this.grade = 2;
    }else if(this.chosenStars == "★★★"){
      this.grade = 3;
    }else if(this.chosenStars == "★★★★"){
      this.grade = 4;
    }else if(this.chosenStars == "★★★★★"){
      this.grade = 5;
    }

    if(this.grade > 0){
    let numOfRating = parseInt(this.chosenMedicine.numOfRating) + 1;
    let finalGrade = (this.grade + parseInt(this.chosenMedicine.averageRating)) / numOfRating;

    this.chosenMedicine.numOfRating = numOfRating;
    this.chosenMedicine.averageRating = finalGrade | 0;

    console.log(this.chosenMedicine);

    this.service.putRate(this.chosenMedicine).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK);
        this.router.navigate(['/UserHomePage'])
      },
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      })
    }

  }

  quit(){
    this.showCard = false;
  }


  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

  home(){
    this.router.navigate(['/UserHomePage'])
  }

  profile(){
    this.router.navigate(['/PatientProfile'])
  }

  backToReview(){
    this.router.navigate(['/Review'])
  }

}
