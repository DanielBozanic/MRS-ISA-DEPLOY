import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { DermatologistReviewService } from './dermatologist-review.service';

@Component({
  selector: 'app-dermatologist-review',
  templateUrl: './dermatologist-review.component.html',
  styleUrls: ['./dermatologist-review.component.css']
})
export class DermatologistReviewComponent implements OnInit {

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  empty:boolean = false;
  showTable:boolean = true;
  showCard:boolean = false;
  displayedColumns: string[] = ["NAME", "SURNAME", "PHONE NUMBER", "E-MAIL", "RATING", "RATE"];
  elems = []
  dataSource = new MatTableDataSource<any>(this.elems);

  chosenDermatologist:any;
  
  chosenStars!:string;
  grade: number = 0;
  stars: string[] = ['★', '★★', '★★★', '★★★★', '★★★★★'];

  

  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  verticalPosition: MatSnackBarVerticalPosition = "top";
  
  constructor(public service:DermatologistReviewService, private snackBar: MatSnackBar, private router:Router) { 
    this.getDermatologist(); 
  }


  ngOnInit(): void {
  }


  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }


  getDermatologist():void{
    this.service.getAllDermatologists().subscribe((data2:any)=>{ //svi dermatolozi
      this.elems = data2;
      this.dataSource = new MatTableDataSource<any>(this.elems);
      this.dataSource.paginator = this.paginator;

      if(this.elems.length == 0){
        this.empty = true;
        this.showTable = false;
      }
      }) 

      
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    this.dataSource.paginator = this.paginator;
    
  }


  
  rate(element:any){
    this.showCard = true;
    this.chosenDermatologist = element;
  }


  sendRating(){
    console.log(this.chosenDermatologist);
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
    let numOfRating = this.chosenDermatologist.numOfRating + 1;
    let finalGrade = (this.grade + this.chosenDermatologist.rating) / numOfRating;

    this.chosenDermatologist.numOfRating = numOfRating;
    this.chosenDermatologist.rating = finalGrade.toFixed(2);;

    console.log(this.chosenDermatologist);

    this.service.putRate(this.chosenDermatologist).subscribe(
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
