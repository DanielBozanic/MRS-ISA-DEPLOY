package com.MRSISA2021_T15.dto;

import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.AppointmentDermatologist;
import com.MRSISA2021_T15.model.MedicineQuantity;

public class AppointmentEndDermatologist {
	AppointmentDermatologist appointment;
	public AppointmentDermatologist getAppointment() {
		return appointment;
	}
	public void setAppointment(AppointmentDermatologist appointment) {
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

