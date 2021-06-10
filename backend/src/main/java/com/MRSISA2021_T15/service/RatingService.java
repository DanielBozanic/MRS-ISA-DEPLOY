package com.MRSISA2021_T15.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.AppointmentDermatologist;
import com.MRSISA2021_T15.model.AppointmentPharmacist;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.EReceiptMedicineDetails;
import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.PatientSubPharmacy;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.repository.AppointmentRepository;
import com.MRSISA2021_T15.repository.EReceiptAndMedicineDetailsRepository;
import com.MRSISA2021_T15.repository.MedicineRepository;
import com.MRSISA2021_T15.repository.PharmacyRepository;
import com.MRSISA2021_T15.repository.ReservationItemRepository;
import com.MRSISA2021_T15.repository.UserRepository;

@Service
public class RatingService {

	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	ReservationItemRepository reservationItemRepository;
	
	@Autowired
	EReceiptAndMedicineDetailsRepository eReceiptAndMedicineDetailsRepository;
	
	@Autowired
	MedicineRepository medicineRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PharmacyRepository pharmacyRepository;
	
	
	
	public List<Dermatologist> findAllDoneDerAppOfPatient(Patient patient) {
		return appointmentRepository.findAllDoneDerAppOfPatient(patient.getId());
	}
	
	public List<Pharmacist> findAllDonePharAppOfPatient(Patient patient){
		return appointmentRepository.findAllDonePharAppOfPatient(patient.getId());
	}
	
	
	public List<Medicine> findAllMedicinesThatPatientCanRate(Patient patient){
		List<Medicine> medicinesThatArePickedUp = reservationItemRepository.findAllMedicinesThatPatientPic(patient.getId());
		
		List<EReceiptMedicineDetails> medicineByERecept = eReceiptAndMedicineDetailsRepository.findAllMedicinesByERecept(patient.getId());
		List<Medicine>allMedicineInBase = medicineRepository.findAllMedicines();
		
		
		for(int i = 0; i<medicineByERecept.size(); i++) {
			String nameOfMedicine = medicineByERecept.get(i).getMedicineName();
			
			for(int j = 0; j<allMedicineInBase.size(); j++) {
				if(nameOfMedicine.equals(allMedicineInBase.get(j).getName())) {
					if(!medicinesThatArePickedUp.contains(allMedicineInBase.get(j))) {
						medicinesThatArePickedUp.add(allMedicineInBase.get(j));
					}
				}
			}
		}
		return medicinesThatArePickedUp;
	}
	
	
	public List<Pharmacy> findAllPharmaciesThatPatientHadApp(Patient patient){
		List<Pharmacy> list1 = appointmentRepository.findAllPharmaciesThatPatientHadApp(patient.getId());
		List<Pharmacy> list2 = reservationItemRepository.findAllPharmaciesThatPatientPicMrd(patient.getId());
		
		for(int i = 0; i<list2.size(); i++) {
			if(!list1.contains(list2.get(i))) {
				list1.add(list2.get(i));
			}
		}
		
		return list1;
	}
	
	
	public void saveDermatologist(Dermatologist dermatologist) {
		Dermatologist der = userRepository.findDermatologistWithId(dermatologist.getId());
		if(der != null) {
			der.setRating(dermatologist.getRating());
			der.setNumOfRating(dermatologist.getNumOfRating());
			userRepository.save(der);
		}
		
	}
	
	public void savePharmacist(Pharmacist pharmacist) {
		Pharmacist phar = userRepository.findPharmacistWithId(pharmacist.getId());
		if(phar != null) {
			phar.setRating(pharmacist.getRating());
			phar.setNumOfRating(pharmacist.getNumOfRating());
			userRepository.save(phar);
		}
	}
	
	
	public void savePharmacy(Pharmacy pharmacy) {
		Pharmacy phar = pharmacyRepository.findPharmacyWithId(pharmacy.getId());
		if(phar != null) {
			phar.setRating(pharmacy.getRating());
			phar.setNumOfRating(pharmacy.getNumOfRating());
			pharmacyRepository.save(pharmacy);
		}
		
	}
	
	
	public void saveMedicine(Medicine medicine) {
		Medicine med = medicineRepository.findMedicineWithId(medicine.getId());
		if(med != null) {
			med.setAverageRating(medicine.getAverageRating());
			med.setNumOfRating(medicine.getNumOfRating());
			medicineRepository.save(med);
		}
	}
	
	
	
	@Scheduled(fixedDelayString = "PT24H")
	public void deletePenals() throws InterruptedException {
		LocalDateTime now = LocalDateTime.now();
		
		if(now.getDayOfMonth() == 1) {
			for(Patient p : userRepository.findAllPatients()) {
				
				p.setPenals(0);
			}
		}
	}
	
	
	
	
}

