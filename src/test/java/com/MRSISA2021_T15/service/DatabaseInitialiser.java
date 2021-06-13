package com.MRSISA2021_T15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.MRSISA2021_T15.model.CategoryName;
import com.MRSISA2021_T15.model.Complaint;
import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.MedicineForm;
import com.MRSISA2021_T15.model.MedicineType;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.repository.ComplaintRepository;
import com.MRSISA2021_T15.repository.MedicineRepository;
import com.MRSISA2021_T15.repository.UserRepository;

@Component
public class DatabaseInitialiser implements ApplicationRunner {
	
	@Autowired
	private MedicineRepository medicineRepository;
	
	@Autowired
	private ComplaintRepository complaintRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Medicine medicine = new Medicine();
		medicine.setAdditionalComments("");
		medicine.setComposition("");
		medicine.setForm(MedicineForm.CAPSULE);
		medicine.setManufacturer("");
		medicine.setMedicineType(MedicineType.ANALGESIC);
		medicine.setName("");
		medicine.setPoints(9);
		medicine.setSubstituteMedicineIds(null);
		medicine.setMedicineCode("s123");
		medicine.setPrescription(false);
		
		medicineRepository.save(medicine);
		
		Patient patient = new Patient();
		patient.setAddress("");
		patient.setCity("");
		patient.setCountry("");
		patient.setName("");
		patient.setPhoneNumber("");
		patient.setSurname("");
		patient.setUsername("lale");
        patient.setEmail("");
		patient.setPassword("");
		patient.setEnabled(true);
		patient.setFirstLogin(false);
		patient.setCategoryName(CategoryName.REGULAR);
		patient.setCollectedPoints(0);
		
		var systemAdmin = new SystemAdmin();
		systemAdmin.setAddress("");
		systemAdmin.setCity("");
		systemAdmin.setCountry("");
		systemAdmin.setName("");
		systemAdmin.setPhoneNumber("");
		systemAdmin.setSurname("");
		systemAdmin.setUsername("rane");
		systemAdmin.setEmail("");
		systemAdmin.setPassword("");
		systemAdmin.setEnabled(true);
		systemAdmin.setFirstLogin(true);
		
		userRepository.save(systemAdmin);
		
		patient = userRepository.save(patient);
		
		Complaint complaint1 = new Complaint();
        complaint1.setText("text");
        complaint1.setPatient(patient);
        
        Complaint complaint2 = new Complaint();
        complaint2.setText("text");
        complaint2.setPatient(patient);
        
        complaintRepository.save(complaint2);
		
	}
}
