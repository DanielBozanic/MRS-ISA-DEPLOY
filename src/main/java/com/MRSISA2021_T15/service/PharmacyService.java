package com.MRSISA2021_T15.service;

import com.MRSISA2021_T15.dto.PharmacyDTO;
import com.MRSISA2021_T15.model.Pharmacy;

import java.util.List;

public interface PharmacyService {
	
	String registerPharmacy(PharmacyDTO pharmacyDto);

	Pharmacy getPharmacyData();

	String updatePharmacyData(PharmacyDTO pharmacyDto);

	List<Pharmacy> getPharmacies();

}
