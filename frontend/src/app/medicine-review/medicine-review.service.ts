import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MedicineReviewService {

  private readonly getMedicinesToRate = 'http://localhost:8080/rating/getMedicineToRate';
  private readonly putMedicineToRate = 'http://localhost:8080/rating/rateMedicine';
  
  constructor(private http: HttpClient) { }

  public getAllMedicines(){
    return this.http.get(this.getMedicinesToRate);
  }

  public putRate(data:any){
    return this.http.put<any>(this.putMedicineToRate, data).pipe(catchError(this.errorHandler));
  }

  public errorHandler(error:HttpErrorResponse){
    return throwError(error);
  }
}
