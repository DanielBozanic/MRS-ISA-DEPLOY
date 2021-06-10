package com.MRSISA2021_T15.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "COMPLAINT_PHARMACIST")
public class ComplaintPharmacist extends Complaint {

	@ManyToOne
	@JoinColumn(name = "pharmacist_id")
	Pharmacist pharmacist;

	public Pharmacist getPharmacist() {
		return pharmacist;
	}

	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}
}
