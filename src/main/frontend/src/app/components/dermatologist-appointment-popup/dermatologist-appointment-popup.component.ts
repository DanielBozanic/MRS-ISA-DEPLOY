import { Component, Inject } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Appointment } from 'src/app/models/appointment';

@Component({
  selector: 'app-dermatologist-appointment-popup',
  templateUrl: './dermatologist-appointment-popup.component.html',
  styleUrls: ['./dermatologist-appointment-popup.component.css'],
})
export class DermatologistAppointmentPopupComponent {
  constructor(
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<DermatologistAppointmentPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Appointment
  ) {}
  appointmentForm = this.formBuilder.group({
    date: '',
    time: '',
    duration: '',
    price: '',
  });

  onNoClick(): void {
    this.dialogRef.close();
  }
}
