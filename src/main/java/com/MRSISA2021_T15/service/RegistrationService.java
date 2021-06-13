package com.MRSISA2021_T15.service;

import org.springframework.web.servlet.ModelAndView;

import com.MRSISA2021_T15.dto.DermatologistDTO;
import com.MRSISA2021_T15.dto.PatientDTO;
import com.MRSISA2021_T15.dto.PharmacyAdminDTO;
import com.MRSISA2021_T15.dto.SupplierDTO;
import com.MRSISA2021_T15.dto.SystemAdminDTO;

public interface RegistrationService {
	
	String registerPatient(PatientDTO patientDto);
	
	ModelAndView confirmAccount(ModelAndView modelAndView, String confirmationToken);
	
	String registerSystemAdmin(SystemAdminDTO systemAdminDto);
	
	String registerDermatologist(DermatologistDTO dermatologistDto);
	
	String registerSupplier(SupplierDTO supplierDto);
	
	String registerPharmacyAdministrator(PharmacyAdminDTO pharmacyAdminDto);

}