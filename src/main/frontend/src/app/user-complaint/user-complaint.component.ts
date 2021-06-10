import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { UserComplaintService } from "./user-complaint.service";
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';



export class Dermatologist{
  id : any;
  email : any;
  name : any;
  surname : any;
  address : any;
  city: any;
  country : any;
  phone_number : any;
  username : any;
  password : any;
  first_login : any;
  rating : any;

  constructor(id:any, email:any, name:any,surname:any, address:any, city:any, country:any, phone_number:any, username:any, password:any,
    first_login:any, rating:any){
      this.id = id;
      this.name = name;
      this.surname = surname;
      this.email = email;
      this.address = address;
      this.city = city;
      this.country = country;
      this.phone_number = phone_number;
      this.username = username;
      this.password = password;
      this.first_login = first_login;
      this.rating = rating;
  }
}



export class Pharmacist{
  id : any;
  email : any;
  name : any;
  surname : any;
  address : any;
  city: any;
  country : any;
  phone_number : any;
  username : any;
  password : any;
  first_login : any;
  rating : any;

  constructor(id:any, email:any, name:any,surname:any, address:any, city:any, country:any, phone_number:any, username:any, password:any,
    first_login:any, rating:any){
      this.id = id;
      this.name = name;
      this.surname = surname;
      this.email = email;
      this.address = address;
      this.city = city;
      this.country = country;
      this.phone_number = phone_number;
      this.username = username;
      this.password = password;
      this.first_login = first_login;
      this.rating = rating;
  }
}



export class Pharmacy{
  id : any;
  name : any;
  address : any;
  city: any;
  country : any;
  description: any;
  rating : any;

  constructor(id:any, name:any, address:any, city:any, country:any, rating:any){
      this.id = id;
      this.name = name;
      this.address = address;
      this.city = city;
      this.country = country;
      this.rating = rating;
  }
}


@Component({
  selector: 'app-user-complaint',
  templateUrl: './user-complaint.component.html',
  styleUrls: ['./user-complaint.component.css']
})
export class UserComplaintComponent implements OnInit {
  showCardD : boolean = true;
  showCardP : boolean = true;
  showCardPy: boolean = true;

  showCardComD: boolean = false;
  showCardComP: boolean = false;
  showCardComPY: boolean= false;

  FormD! : FormGroup;
  FormP! : FormGroup;
  FormPY! : FormGroup;


  dermatologistForm! : FormControl;
  pharmacistForm!: FormControl;
  pharmacyForm!:FormControl;

  FormDC! :FormGroup;
  FormPC! :FormGroup;
  FormPYC! :FormGroup;

  dermatologistList = [];
  pharmacistList = [];
  pharmacyList = [];

  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  verticalPosition: MatSnackBarVerticalPosition = "top";

  data1:any
  data2:any
  dermatologist!:Dermatologist;
  pharmacist!:Pharmacist;
  pharmacy!:Pharmacy;


  constructor(private fb: FormBuilder, private service: UserComplaintService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.dermatologistForm = new FormControl('dermatologist');
    this.pharmacistForm = new FormControl('pharmacist');
    this.pharmacyForm =  new FormControl('pharmacy');


    this.FormD = this.fb.group({
      id: ['', Validators.required],
      dermatologist: ['', Validators.required]
    });
    this.FormP = this.fb.group({
      id: ['', Validators.required],
      pharmacist: ['', Validators.required]
    });
    this.FormPY = this.fb.group({
      id: ['', Validators.required],
      pharmacy: ['', Validators.required]
    });


    
    
    this.FormDC = this.fb.group({
      text: ['', Validators.required],
      dermaId: ['', Validators.required],
      name:['', Validators.required]
    });
    this.FormPC = this.fb.group({
      text: ['', Validators.required]
    });
    this.FormPYC = this.fb.group({
      text: ['', Validators.required]
    });


    this.dermatologist = new Dermatologist(0, "email@.hotmail.com", "Imenko", "Prezimic", "Adresic", "Gradic","Drzavica", "0000000000", "Username", "pass", false, 0);
    this.pharmacist = new Pharmacist(0, "email@.hotmail.com", "Imenko", "Prezimic", "Adresic", "Gradic","Drzavica", "0000000000", "Username", "pass", false, 0);
    this.pharmacy = new Pharmacy(0, "Ime", "Adresa", "Grad", "Drzava", 0);

    this.service.getDermatologist().subscribe((data:any) => {this.dermatologistList = data; console.log(this.dermatologistList)});
    this.service.getPharmacist().subscribe((data:any) => {this.pharmacistList = data; console.log(this.pharmacistList)});
    this.service.getPharmacy().subscribe((data:any) => {this.pharmacyList = data; console.log(this.pharmacyList)});
  }


  checkDermatologist(){
    this.data1 = this.FormD.get("id");
    if(this.data1.value === null || this.data1.value === '' || this.data1.value === " "){
      this.FormD.controls["id"].hasError("You didn't input id");
      this.openSnackBar("You didn't input id", this.RESPONSE_ERROR);
      this.dermatologistForm.reset();
      this.FormD.reset();
    }else{
      this.service.checkD(this.FormD.value).subscribe(
        response => {
          console.log(response);
          this.openSnackBar(response, this.RESPONSE_OK);
          this.dermatologistForm.reset();
          this.showCardD = false;
          this.showCardPy = false;
          this.showCardP = false;
          this.showCardComD = true;
        },
        error => {
          console.log(error);
          this.openSnackBar(error.error, this.RESPONSE_ERROR);
          this.dermatologistForm.reset();
          this.FormD.reset();
        }
      );
    }
    
  }




  checkPharmacist(){
    this.data1 = this.FormP.get("id");
    if(this.data1.value === null || this.data1.value === '' || this.data1.value === " "){
      this.FormP.controls["id"].hasError("You didn't input id");
      this.openSnackBar("You didn't input id", this.RESPONSE_ERROR);
      this.pharmacistForm.reset();
    }else{
      this.service.checkP(this.FormP.value).subscribe(
        response => {
          console.log(response);
          this.openSnackBar(response, this.RESPONSE_OK);
          this.pharmacistForm.reset();
          this.showCardP = false;
          this.showCardComP = true;
          this.showCardD = false;
          this.showCardPy = false;
        },
        error => {
          console.log(error);
          this.openSnackBar(error.error, this.RESPONSE_ERROR);
          this.pharmacistForm.reset();
          this.FormP.reset();
        }
      );
    }
  }





  checkPharmacy(){
    this.data1 = this.FormPY.get("id");
    if(this.data1.value === null || this.data1.value === '' || this.data1.value === " "){
      this.FormPY.controls["id"].hasError("You didn't input id");
      this.openSnackBar("You didn't input id", this.RESPONSE_ERROR);
      this.pharmacyForm.reset();
    }else{
      this.service.checkPY(this.FormPY.value).subscribe(
        response => {
          console.log(response);
          this.openSnackBar(response, this.RESPONSE_OK);
          this.pharmacyForm.reset();
          this.showCardD = false;
          this.showCardPy = false;
          this.showCardP = false;
          this.showCardComPY = true;
        },
        error => {
          console.log(error);
          this.openSnackBar(error.error, this.RESPONSE_ERROR);
          this.pharmacyForm.reset();
          this.FormPY.reset();
        }
      );
    }
  }


  complaintDerm(){
    this.data1 = this.FormDC.get("text");
    this.data2 = this.FormD.get('id');
   
    console.log(this.data2.value)

    let der = {
      "id" : this.data2.value
    }
    let complaint = {
      "text": this.data1.value,
      "dermatologist" : der
    }
    
    if(this.data1.value === null || this.data1.value === '' || this.data1.value === " "){
      this.openSnackBar("You didn't input complaint", this.RESPONSE_ERROR);
      this.FormDC.reset()
      
    }else{
      this.service.postD(complaint).subscribe(
        response => {
          console.log(response);
          this.openSnackBar(response, this.RESPONSE_OK);
          this.FormD.reset();
          this.FormDC.reset();
        },
        error => {
          console.log(error);
          this.openSnackBar(error.error, this.RESPONSE_ERROR);
          this.FormDC.reset();
        })
    }

  }



  complaintPhar(){
    this.data1 = this.FormPC.get("text");
    this.data2 = this.FormP.get('id');
   
    console.log(this.data2.value)

    let pharm = {
      "id" : this.data2.value
    }
    let complaint = {
      "text": this.data1.value,
      "pharmacist" : pharm
    }
    
    
    
    console.log(this.data1.value);
    if(this.data1.value === null || this.data1.value === '' || this.data1.value === " "){
      this.openSnackBar("You didn't input complaint", this.RESPONSE_ERROR);
      this.FormPC.reset()
      
    }else{
      this.service.postP(complaint).subscribe(
        response => {
          console.log(response);
          this.openSnackBar(response, this.RESPONSE_OK);
          this.FormPC.reset()
        },
        error => {
          console.log(error);
          this.openSnackBar(error.error, this.RESPONSE_ERROR);
          this.FormPC.reset()
        })
    }
  }



  complaintPy(){
    this.data1 = this.FormPYC.get("text");
    this.data2 = this.FormPY.get('id');
   
    console.log(this.data2.value)

    let py = {
      "id" : this.data2.value
    }
    let complaint = {
      "text": this.data1.value,
      "pharmacy" : py
    }

    if(this.data1.value === null || this.data1.value === '' || this.data1.value === " "){
      this.openSnackBar("You didn't input complaint", this.RESPONSE_ERROR);
      this.FormPYC.reset()
      
    }else{
      this.service.postPY(complaint).subscribe(
        response => {
          console.log(response);
          this.openSnackBar(response, this.RESPONSE_OK);
          this.FormPY.reset()
          this.FormPYC.reset()
        },
        error => {
          console.log(error);
          this.openSnackBar(error.error, this.RESPONSE_ERROR);
          this.FormPYC.reset()
        })
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
