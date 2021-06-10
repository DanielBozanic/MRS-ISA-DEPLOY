package com.MRSISA2021_T15.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.model.Allergy;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.MedicineNeeded;
import com.MRSISA2021_T15.model.MedicinePharmacy;
import com.MRSISA2021_T15.model.MedicineQuantity;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.model.PharmacyAdmin;
import com.MRSISA2021_T15.repository.MedicineNeededRepository;
import com.MRSISA2021_T15.repository.UserRepository;
import com.MRSISA2021_T15.service.AllergyService;
import com.MRSISA2021_T15.service.EmailSenderService;
import com.MRSISA2021_T15.service.MedicinePharmacyService;
import com.MRSISA2021_T15.service.MedicineService;
import com.MRSISA2021_T15.service.SubstituteMedicineService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/allergies")
public class AllergyController {

	@Autowired
	AllergyService service;
	
	@Autowired
	MedicinePharmacyService service1;
	
	@Autowired
	SubstituteMedicineService service2;
	
	@Autowired
	MedicineNeededRepository repo;
	
	@Autowired
	UserRepository adminsrepo;
	
	@Autowired
	JavaMailSender emailsending;
	@Autowired
	Environment envi;
	
	
	
	
	
	
	@GetMapping(value = "/checkForAllergiesPharmacist/pharmacy={pharmacyId}medicine={medicineId}patient={patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public List<MedicineQuantity> getMedicinePharmacist(@PathVariable("pharmacyId") Integer id, @PathVariable("medicineId") Integer medicineId, @PathVariable("patientId") Integer patientId) throws InterruptedException {
		List<MedicinePharmacy> medps = service1.medcineInPharmacy(id);
		for (MedicinePharmacy medicine : medps) {
			if(medicine.getMedicine().getId() == medicineId) {
				if(medicine.getAmount() == 0) {
					saveNeededs(medicine.getMedicine(), medicine.getPharmacy());
					return dummySubs(medicineId, patientId, medps);
				}
				break;
			}
		}
		List<Allergy> allergies = service.getForPatient(patientId);
		for (Allergy allergy : allergies) {
			if(allergy.getMedicine().getId() == medicineId) {
				return dummySubs(medicineId, patientId, medps);
			}
		}
		return new ArrayList<MedicineQuantity>();
	}
	
	@GetMapping(value = "/checkForAllergiesDermatologist/pharmacy={pharmacyId}medicine={medicineId}patient={patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public List<MedicineQuantity> getMedicineDermatologist(@PathVariable("pharmacyId") Integer id, @PathVariable("medicineId") Integer medicineId, @PathVariable("patientId") Integer patientId) throws InterruptedException {
		List<MedicinePharmacy> medps = service1.medcineInPharmacy(id);
		for (MedicinePharmacy medicine : medps) {
			if(medicine.getMedicine().getId() == medicineId) {
				if(medicine.getAmount() == 0) {
					saveNeededs(medicine.getMedicine(), medicine.getPharmacy());
					return dummySubs(medicineId, patientId, medps);
				}
				break;
			}
		}
		List<Allergy> allergies = service.getForPatient(patientId);
		for (Allergy allergy : allergies) {
			if(allergy.getMedicine().getId() == medicineId) {
				return dummySubs(medicineId, patientId, medps);
			}
		}
		return new ArrayList<MedicineQuantity>();
	}
	
	public List<MedicineQuantity> dummySubs(Integer medicineId, Integer patientId, List<MedicinePharmacy> medps){
		boolean allergic = false;
		List<Medicine> subs1 = service2.getForMedicine(medicineId);
		ArrayList<MedicineQuantity> subs = new ArrayList<>();
		List<Allergy> allergies = service.getForPatient(patientId);
		for (Medicine medicine : subs1) {
			for (Allergy allergy : allergies) {
				if(allergy.getMedicine().getId() == medicine.getId()) {
					allergic = true;
					break;
				}
			}
			if(!allergic) {
				for(MedicinePharmacy m : medps) {
					if(m.getMedicine().getId() == medicine.getId()) {
						MedicineQuantity med = new MedicineQuantity();
						med.setMedicine(medicine);
						med.setQuantity(m.getAmount());
						subs.add(med);
					}
				}				
			}
			allergic = false;
		}
		if(subs.size() == 0) {
			MedicineQuantity dummy = new MedicineQuantity();
			dummy.setMedicine(new Medicine());
			dummy.getMedicine().setName("ERROR");
			subs.add(dummy);
		}
		return subs;
	}
	
	
	public void saveNeededs(Medicine medicine, Pharmacy pharmacy) throws InterruptedException {
		MedicineNeeded med = new MedicineNeeded();
		med.setMedicine(medicine);
		med.setPharmacy(pharmacy);
		med.setRequested(LocalDateTime.now());
		repo.save(med);
		
		Thread thread = new Thread() {
			public void run() {
				mailing( pharmacy, medicine);
			}
		};
		thread.start();
	}
	
	public void mailing(Pharmacy pharmacy, Medicine medicine) {
		for (PharmacyAdmin pharmacyAdmin : adminsrepo.findAllPharmacyAdmins()) {
			if(pharmacyAdmin.getPharmacy().getId() == pharmacy.getId()) {
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(pharmacyAdmin.getEmail());
				mailMessage.setSubject("Medicine not in stock");
				mailMessage.setFrom(envi.getProperty("spring.mail.username"));
				mailMessage.setText("Medicine " + medicine.getName() + " was requested in pharmacy " + pharmacy.getName()
				+ ". You should consider restocking this medicine.");
				emailsending.send(mailMessage);
				//Thread.sleep(3000);
			}
		}
	}
	
	
	
	
	
	@GetMapping(value = "/getAllMedicines", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	List<Medicine> getAllMedicines(){
		
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Medicine> medicines = service.findAllMedicines();
		List<Allergy> allergies = service.getForPatient(p.getId());
		
		List<Medicine> returnMedicine = new ArrayList<Medicine>();
		
		boolean flag = false;
		
		for(Medicine m: medicines){
			for(Allergy a : allergies) {
				if(a.getMedicine().getId() == m.getId()) {
					flag = true;
				}
			}
			if(flag == false) {
				returnMedicine.add(m);
			}
			flag = false;
		}
		return returnMedicine;
		
	}
	
	
	
	
	@GetMapping(value = "/getAllAllergies", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	List<Allergy> getAllAllergies(){
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return service.getForPatient(p.getId());
	}
	
	
	
	
	@PutMapping(value = "/addAllergy", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>addAllergy(@RequestBody Medicine medicine){
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		service.addAllergy(medicine, p);
		Gson gson = new GsonBuilder().create();
		return new ResponseEntity<String>(gson.toJson("You added new allergy."), HttpStatus.OK);
		
	}
}


