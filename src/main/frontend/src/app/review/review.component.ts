import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }


  showDermatologists(){
    this.router.navigate(['/DermatologistReview'])
  }

  showPharmacists(){
    this.router.navigate(['/PharmacistReview'])
  }

  showPharmacies(){
    this.router.navigate(['/PharmacyReview'])
  }

  showMedicines(){
    this.router.navigate(['/MedicineReview'])
  }

  exit(){
    this.router.navigate(['/UserHomePage'])
  }

  home(){
    this.router.navigate(['/UserHomePage'])
  }

  profile(){
    this.router.navigate(['/PatientProfile'])
  }

}
