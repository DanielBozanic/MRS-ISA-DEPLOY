import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { PharmacyReviewService } from './pharmacy-review.service';

@Component({
  selector: 'app-pharmacy-review',
  templateUrl: './pharmacy-review.component.html',
  styleUrls: ['./pharmacy-review.component.css']
})
export class PharmacyReviewComponent implements OnInit {
  

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  displayedColumns: string[] = ['name', 'address', 'city', 'country', 'rating', 'rate'];
  showTable:boolean = true;
  showCard:boolean = false;

  elems = []
  dataSource = new MatTableDataSource<any>(this.elems);
  
  chosenPharmacy:any;
  empty:boolean = false;
  chosenStars!:string;
  grade: number = 0;
  stars: string[] = ['★', '★★', '★★★', '★★★★', '★★★★★'];
  

  
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  verticalPosition: MatSnackBarVerticalPosition = "top";

  
  constructor(public service:PharmacyReviewService, private snackBar: MatSnackBar, private router:Router) { 
    this.getPharmacies(); 
  }


  ngOnInit(): void {
  }


  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }


  getPharmacies():void{
    this.service.getAllPharmacies().subscribe((data2:any)=>{ 
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
    this.chosenPharmacy = element;
  }


  sendRating(){
    console.log(this.chosenPharmacy);
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
    let numOfRating = this.chosenPharmacy.numOfRating + 1;
    let finalGrade = (this.grade + this.chosenPharmacy.rating) / numOfRating;

    this.chosenPharmacy.numOfRating = numOfRating;
    this.chosenPharmacy.rating = finalGrade.toFixed(2);;

    console.log(this.chosenPharmacy);

    this.service.putRate(this.chosenPharmacy).subscribe(
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
