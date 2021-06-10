package com.MRSISA2021_T15.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue(value = "APPOINTMENT_DERMATOLOGIST")
public class AppointmentDermatologist extends Appointment{
	@ManyToOne
	@JoinColumn(name = "dermatologist_id")
	private Dermatologist dermatologist;

	public Dermatologist getDermatologist() {
		return dermatologist;
	}

	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}
	
}
