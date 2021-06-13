package com.MRSISA2021_T15.dto;

import com.MRSISA2021_T15.model.Pharmacist;

public class AppointmentPharmacistDTO extends AppointmentDTO {

	private Pharmacist pharmacist;

	public Pharmacist getPharmacist() {
		return pharmacist;
	}

	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}
}
