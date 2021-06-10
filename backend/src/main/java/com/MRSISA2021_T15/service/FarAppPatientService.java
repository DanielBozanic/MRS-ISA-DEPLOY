package com.MRSISA2021_T15.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.MRSISA2021_T15.model.EmploymentPharmacist;
import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.repository.EmploymentRepository;

@Service
public class FarAppPatientService {

	@Autowired
	private EmploymentRepository eRepository;
	
	
	public List<EmploymentPharmacist> findAllPharmacist(){
		return eRepository.findAllPharmacist();
	}
	
	
	public List<EmploymentPharmacist> findPharmacyByTime(){
		return eRepository.findAllPharmacist();
	}
	
	
	
	
}
