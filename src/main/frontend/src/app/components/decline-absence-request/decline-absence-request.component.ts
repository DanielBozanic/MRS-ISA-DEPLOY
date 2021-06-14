import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
export interface DialogData {
  message: string;
}
@Component({
  selector: 'app-decline-absence-request',
  templateUrl: './decline-absence-request.component.html',
  styleUrls: ['./decline-absence-request.component.css'],
})
export class DeclineAbsenceRequestComponent {
  constructor(
    public dialogRef: MatDialogRef<DeclineAbsenceRequestComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
