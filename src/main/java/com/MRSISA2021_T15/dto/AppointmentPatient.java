package com.MRSISA2021_T15.dto;

import java.time.LocalDateTime;

import com.MRSISA2021_T15.model.Patient;

public class AppointmentPatient {

	private Patient patient;
	private LocalDateTime date;
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
}
