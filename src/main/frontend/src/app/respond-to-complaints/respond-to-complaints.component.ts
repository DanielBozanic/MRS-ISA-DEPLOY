import { AfterViewInit, Component, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from '../login/auth.service';
import { RespondToComplaintsService } from './respond-to-complaints.service';

@Component({
  selector: 'app-respond-to-complaints',
  templateUrl: './respond-to-complaints.component.html',
  styleUrls: ['./respond-to-complaints.component.css']
})
export class RespondToComplaintsComponent implements OnInit, AfterViewInit {

  displayedColumnsComplaintsToRespond: string[] = ['id', 'complaint', 'username'];
  displayedColumnsResponses: string[] = ['id', 'complaint', 'username', 'response'];
  complaintsToRespond = [];
  responses = [];
  complaintsToRespondDataSource = new MatTableDataSource<any>(this.complaintsToRespond);
  responsesDataSource = new MatTableDataSource<any>(this.responses);
  responseForm!: FormGroup;
  verticalPosition: MatSnackBarVerticalPosition = "top";
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  constructor
  (
    private respondToCompaintsService: RespondToComplaintsService,
    private fb: FormBuilder,
    private _snackBar: MatSnackBar,
    private authService: AuthService
  ) { }

  @ViewChildren(MatPaginator) paginator = new QueryList<MatPaginator>();

  ngOnInit(): void {
    this.responseForm = this.fb.group(
      {
        id: ['', Validators.required],
        response: ['', Validators.required]
      }
    )
    this.getComplaintsToRespond();
    this.getResponses();
  }

  ngAfterViewInit(): void {
    this.complaintsToRespondDataSource.paginator = this.paginator.toArray()[0];
    this.responsesDataSource.paginator = this.paginator.toArray()[1];
  }

  public hasError = (controlName: string, errorName: string) =>{
    return this.responseForm.controls[controlName].hasError(errorName);
  }

  getComplaintsToRespond() {
    this.respondToCompaintsService.getComplaintsToRespond().subscribe(
      data => {
        this.complaintsToRespond = data;
        this.complaintsToRespondDataSource = new MatTableDataSource<any>(this.complaintsToRespond);
        this.complaintsToRespondDataSource.paginator = this.paginator.toArray()[0];
      }
    )
  }

  getResponses() {
    this.respondToCompaintsService.getResponses().subscribe(
      data => {
        this.responses = data;
        this.responsesDataSource = new MatTableDataSource<any>(this.responses);
        this.responsesDataSource.paginator = this.paginator.toArray()[1];
      }
    )
  }

  sendResponse() {
    this.respondToCompaintsService.sendResponse(this.responseForm.value).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK);
        this.getComplaintsToRespond();
        this.getResponses();
      }, 
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      }
    );
  }

  openSnackBar(msg: string, responseCode: number) {
    this._snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

  logout() {
    this.authService.logout();
  }
}
