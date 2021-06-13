package com.MRSISA2021_T15.dto;


import com.MRSISA2021_T15.model.Pharmacy;

public class ComplaintPharmacyDTO extends ComplaintDTO {

	private Pharmacy pharmacy;

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
}
