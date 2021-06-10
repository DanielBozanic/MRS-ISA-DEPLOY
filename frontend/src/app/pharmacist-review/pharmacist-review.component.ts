import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { PharmacistReviewService } from './pharmacist-review.service';

@Component({
  selector: 'app-pharmacist-review',
  templateUrl: './pharmacist-review.component.html',
  styleUrls: ['./pharmacist-review.component.css']
})
export class PharmacistReviewComponent implements OnInit {
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  showTable:boolean = true;
  showCard:boolean = false;
  displayedColumns: string[] = ["NAME", "SURNAME", "PHONE NUMBER", "E-MAIL", "RATING", "RATE"];
  elems = []
  dataSource = new MatTableDataSource<any>(this.elems);
  empty:boolean = false;
  chosenPharmacist:any;
  
  chosenStars!:string;
  grade: number = 0;
  stars: string[] = ['★', '★★', '★★★', '★★★★', '★★★★★'];

  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  verticalPosition: MatSnackBarVerticalPosition = "top";


  constructor(public service:PharmacistReviewService, private snackBar: MatSnackBar, private router:Router) { 
    this.getPharmacists(); 
  }


  ngOnInit(): void {
  }


  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }


  getPharmacists():void{
    this.service.getAllPharmacists().subscribe((data2:any)=>{ 
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
    this.chosenPharmacist = element;
  }


  sendRating(){
    console.log(this.chosenPharmacist);
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
      let numOfRating = this.chosenPharmacist.numOfRating + 1;
      let finalGrade = (this.grade + this.chosenPharmacist.rating) / numOfRating;

      this.chosenPharmacist.numOfRating = numOfRating;
      this.chosenPharmacist.rating = finalGrade.toFixed(2);;

      console.log(this.chosenPharmacist);

      this.service.putRate(this.chosenPharmacist).subscribe(
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
