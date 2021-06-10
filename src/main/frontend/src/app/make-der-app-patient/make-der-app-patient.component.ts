import {AfterViewInit, Component, ViewChild, OnInit} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { MakeDerAppPatientService } from './make-der-app-patient.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-make-der-app-patient',
  templateUrl: './make-der-app-patient.component.html',
  styleUrls: ['./make-der-app-patient.component.css']
})
export class MakeDerAppPatientComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = ['name', 'address', 'city', 'country', 'rate', 'homepage'];
  dataSource!: MatTableDataSource<any>;

  pharmacies: any = [];
  firstForm! : FormGroup;
  showTable: boolean = true;
  formInput: any;


  showHomePage: boolean = false;
  pharmacyData: any;

  freeApp: any;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  

  constructor(private fb: FormBuilder, public service:MakeDerAppPatientService, private router:Router) {
    
  }

  ngOnInit(){
    this.dataSource = new MatTableDataSource( this.pharmacies );
    this.getPharmacyData()
  }


  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }


  public getPharmacyData(){
    this.service.getPharmacies().subscribe((data:any)=>{
      this.pharmacies = data;
      this.dataSource.data = this.pharmacies;
      console.log(this.dataSource);
    })
  }



  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }


  makeHomePage(row:any){
    this.showTable = false;
    this.showHomePage = true;

    this.pharmacyData = row;
    console.log(row)
  }

  pharmacyInfoo:any;

  dermaApp(pharmacyData:any){
    console.log(pharmacyData);
    this.pharmacyInfoo = pharmacyData;
    console.log(this.pharmacyInfoo);
    this.router.navigate(['/MakeDermatologistAppointmentPart2'], {state:{data:{pharmacyInfo:pharmacyData}}});
  }


}


