package com.MRSISA2021_T15.service;

import com.MRSISA2021_T15.model.Pharmacy;

import java.util.List;

public interface PharmacyService {
	
	String registerPharmacy(Pharmacy pharmacy);

	Pharmacy getPharmacyData();

	String updatePharmacyData(Pharmacy pharmacy);

	List<Pharmacy> getPharmacies();

}
