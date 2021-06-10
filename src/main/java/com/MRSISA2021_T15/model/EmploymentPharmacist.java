package com.MRSISA2021_T15.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue(value = "EMPLOYMENT_PHARMACIST")
public class EmploymentPharmacist extends Employment{
	@OneToOne
	//@JoinColumn(name = "pharmacist_id")
	private Pharmacist pharmacist;

	public Pharmacist getPharmacist() {
		return pharmacist;
	}

	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}
	
	
}
