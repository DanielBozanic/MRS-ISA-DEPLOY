package com.MRSISA2021_T15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.repository.PatientSearchRepository;
import com.MRSISA2021_T15.repository.UserRepository;

@Service
public class PatientSearchService {
	
	@Autowired
	private PatientSearchRepository repository;
	
	@Autowired
	private UserRepository patientsRepo;

	public List<Patient> patientsPharmacist(String startsWith){
		return repository.findAllByUsernamePharmacist(startsWith);
	}
	
	public List<Patient> patientsPharmacist(){
		return repository.findAllPharmacist();
	}
	
	public List<Patient> findAll(){
		return repository.findPatients();
	}
	
	public List<Appointment> all(){
		return repository.findAllByOrderByStartDesc();
	}
	
	public List<Patient> allPharmacist(Integer id){
		return repository.findPatientsPharmacist(id);
	}
	
	public List<Patient> allPatients(){
		return patientsRepo.findAllPatients();
	}
}
