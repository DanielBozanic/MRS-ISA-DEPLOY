package com.MRSISA2021_T15.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.dto.DermatologistDTO;
import com.MRSISA2021_T15.dto.MedicineDTO;
import com.MRSISA2021_T15.dto.PharmacistDTO;
import com.MRSISA2021_T15.dto.PharmacyDTO;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.EReceiptMedicineDetails;
import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.Patient;
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
	
	
	public void saveDermatologist(DermatologistDTO dermatologistDto) {
		Dermatologist der = userRepository.findDermatologistWithId(dermatologistDto.getId());
		if(der != null) {
			der.setRating(dermatologistDto.getRating());
			der.setNumOfRating(dermatologistDto.getNumOfRating());
			userRepository.save(der);
		}
		
	}
	
	public void savePharmacist(PharmacistDTO pharmacistDto) {
		Pharmacist phar = userRepository.findPharmacistWithId(pharmacistDto.getId());
		if(phar != null) {
			phar.setRating(pharmacistDto.getRating());
			phar.setNumOfRating(pharmacistDto.getNumOfRating());
			userRepository.save(phar);
		}
	}
	
	
	public void savePharmacy(PharmacyDTO pharmacyDto) {
		Pharmacy phar = pharmacyRepository.findPharmacyWithId(pharmacyDto.getId());
		if(phar != null) {
			phar.setRating(pharmacyDto.getRating());
			phar.setNumOfRating(pharmacyDto.getNumOfRating());
			pharmacyRepository.save(phar);
		}
		
	}
	
	
	public void saveMedicine(MedicineDTO medicineDto) {
		Medicine med = medicineRepository.findMedicineWithId(medicineDto.getId());
		if(med != null) {
			med.setAverageRating(medicineDto.getAverageRating());
			med.setNumOfRating(medicineDto.getNumOfRating());
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

