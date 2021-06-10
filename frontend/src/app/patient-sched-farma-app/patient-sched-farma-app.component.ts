import { DataRowOutlet } from '@angular/cdk/table';
import { analyzeAndValidateNgModules } from '@angular/compiler';
import { Component, ComponentFactoryResolver, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { PatientSchedFarmaAppService } from './patient-sched-farma-app.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-patient-sched-farma-app',
  templateUrl: './patient-sched-farma-app.component.html',
  styleUrls: ['./patient-sched-farma-app.component.css']
})
export class PatientSchedFarmaAppComponent implements OnInit {

  timeForm! : FormGroup;
  start:any;
  startDate:any;

  minDate:any;
  maxDate:any;

  constructor(
    private fb: FormBuilder,
    public service: PatientSchedFarmaAppService,
    private router:Router
    ) { }

  ngOnInit(): void {
    this.timeForm = this.fb.group(
      {
        startDate: ['', Validators.required],
        start: ['', Validators.required],
      }
    );

    const currentYear = new Date().getFullYear();
    const currentMonth = new Date().getMonth();
    
    
    this.minDate = new Date();
    this.maxDate = new Date(currentYear, currentMonth+13, 0);

  }


  public hasError = (controlName: string, errorName: string) =>{
    return this.timeForm.controls[controlName].hasError(errorName);
  }

  date:any;
  
  schedule(){
        this.start = this.timeForm.get("start");
        this.startDate = this.timeForm.get("startDate");
      

        const startic = new Date(this.timeForm.get("startDate")?.value);
        let spliting = this.timeForm.get("start")?.value.split(':')
        let spliting2 = spliting[1].split(' ');

        if(spliting2[1]==="PM"){
          const hours = Number(spliting[0])+12
          const minutes = Number(spliting2[0])
          this.date = new Date(startic.getFullYear(), startic.getMonth(), startic.getDate(), hours, minutes);
          this.givePharmacies(hours);
          
        }else{
          const hours = Number(spliting[0])
          const minutes = Number(spliting2[0])
          this.date = new Date(startic.getFullYear(), startic.getMonth(), startic.getDate(), hours, minutes);
          this.givePharmacies(hours);
        }
  }


  employment:any = [];
  canceled:any = [];
  
  givePharmacies(hours:number){
    this.service.getPharmacistData().subscribe((data:any)=>{
      this.employment = data.filter((el: { start: any ; end:any}) =>{
        if(el.start <= hours && el.end >= hours){
          return el;
        }
        return;
      })

      this.canceledApp();
    })

  }



  canceledApp(){
    this.service.getCanceled().subscribe((data2:any) => {
      this.canceled = data2;
      this.router.navigate(['/PatientScheduleFarmaApp2'], {state:{data:{date:this.date, employments:this.employment, canceled: this.canceled}}});
    })

  }
  

}
