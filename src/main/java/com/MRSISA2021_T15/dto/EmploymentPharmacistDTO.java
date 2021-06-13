package com.MRSISA2021_T15.dto;

import com.MRSISA2021_T15.model.Pharmacist;

public class EmploymentPharmacistDTO extends EmploymentDTO {

	private Pharmacist pharmacist;

	public Pharmacist getPharmacist() {
		return pharmacist;
	}

	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}
}
