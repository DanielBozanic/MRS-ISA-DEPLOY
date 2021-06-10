package com.MRSISA2021_T15.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "APPOINTMENT_PHARMACIST")
public class AppointmentPharmacist extends Appointment {
	@ManyToOne
	//@JoinColumn(name = "pharmacist_id")
	Pharmacist pharmacist;

	public Pharmacist getPharmacist() {
		return pharmacist;
	}

	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}
}
