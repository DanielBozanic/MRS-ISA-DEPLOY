package com.MRSISA2021_T15.dto;

import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.AppointmentPharmacist;
import com.MRSISA2021_T15.model.MedicineQuantity;

public class AppointmentEnd {
	AppointmentPharmacist appointment;
	public AppointmentPharmacist getAppointment() {
		return appointment;
	}
	public void setAppointment(AppointmentPharmacist appointment) {
		this.appointment = appointment;
	}
	MedicineQuantity[] meds;
	String comments;
	
	public MedicineQuantity[] getMeds() {
		return meds;
	}
	public void setMeds(MedicineQuantity[] meds) {
		this.meds = meds;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
