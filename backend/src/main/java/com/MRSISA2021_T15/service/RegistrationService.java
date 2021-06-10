package com.MRSISA2021_T15.service;

import org.springframework.web.servlet.ModelAndView;

import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.PharmacyAdmin;
import com.MRSISA2021_T15.model.Supplier;
import com.MRSISA2021_T15.model.SystemAdmin;

public interface RegistrationService {
	
	String registerPatient(Patient patient);
	
	ModelAndView confirmAccount(ModelAndView modelAndView, String confirmationToken);
	
	String registerSystemAdmin(SystemAdmin systemAdmin);
	
	String registerDermatologist(Dermatologist dermatologist);
	
	String registerSupplier(Supplier supplier);
	
	String registerPharmacyAdministrator(PharmacyAdmin pharmacyAdmin);

}