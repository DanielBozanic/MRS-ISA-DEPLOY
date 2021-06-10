package com.MRSISA2021_T15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Allergy;
import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.repository.AllergyRepository;
import com.MRSISA2021_T15.repository.MedicineRepository;

@Service
public class AllergyService {

	@Autowired
	AllergyRepository repository;
	
	@Autowired
	MedicineRepository medicineRepository;
	
	public List<Allergy> getForPatient(Integer id){
		return repository.findAllPatients(id);
	}
	
	
	public List<Medicine> findAllMedicines(){
		return medicineRepository.findAllMedicines();
	}
	
	
	public void addAllergy(Medicine medicine, Patient patient) {
		Allergy a = new Allergy();
		a.setMedicine(medicine);
		a.setPatient(patient);
		repository.save(a);
	}
	
}
