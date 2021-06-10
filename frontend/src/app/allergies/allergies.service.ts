import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AllergiesService {

  private readonly getMedicines = 'http://localhost:8080/allergies/getAllMedicines';
  private readonly getAllergies = 'http://localhost:8080/allergies/getAllAllergies';
  private readonly addAllergie = 'http://localhost:8080/allergies/addAllergy';
  
  constructor(private http: HttpClient) { }

  public getAllMedicines(){
    return this.http.get(this.getMedicines);
  }

  public getAllAllergies(){
    return this.http.get(this.getAllergies);
  }

  public addAllergy(data:any){
    return this.http.put<any>(this.addAllergie, data).pipe(catchError(this.errorHandler));
  }

  public errorHandler(error:HttpErrorResponse){
    return throwError(error);
  }

  
}
