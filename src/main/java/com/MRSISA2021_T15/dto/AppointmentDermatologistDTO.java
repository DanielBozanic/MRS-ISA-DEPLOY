package com.MRSISA2021_T15.dto;

import com.MRSISA2021_T15.model.Dermatologist;

public class AppointmentDermatologistDTO extends AppointmentDTO {

	private Dermatologist dermatologist;

	public Dermatologist getDermatologist() {
		return dermatologist;
	}

	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}
}
