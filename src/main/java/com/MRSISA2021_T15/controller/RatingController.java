package com.MRSISA2021_T15.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.dto.DermatologistDTO;
import com.MRSISA2021_T15.dto.MedicineDTO;
import com.MRSISA2021_T15.dto.PharmacistDTO;
import com.MRSISA2021_T15.dto.PharmacyDTO;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.service.RatingService;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/rating")
public class RatingController {
	@Autowired
	RatingService ratingService;
	
	private final String review = "Thank you for your review.";
	

	@GetMapping(value = "/getDermatologistToRate", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public List<Dermatologist> getAllDermatologists(){
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ratingService.findAllDoneDerAppOfPatient(p);
	}
	
	@GetMapping(value = "/getPharmacistsToRate", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public List<Pharmacist> getPharmacistsToRate(){
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ratingService.findAllDonePharAppOfPatient(p);
	}
	
	@GetMapping(value = "/getPharmaciesToRate", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public List<Pharmacy> getPharmaciesToRate(){
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ratingService.findAllPharmaciesThatPatientHadApp(p);
	}
	
	@GetMapping(value = "/getMedicineToRate", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public List<Medicine> getMedicineToRate(){
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ratingService.findAllMedicinesThatPatientCanRate(p);
	}
	
	@GetMapping(value = "/getPenalties", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public Patient getPenalties(){
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return p;
	}
	
	@PutMapping(value = "/rateDermatologist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>rateDermatologist(@RequestBody DermatologistDTO dermatologistDto){
		ratingService.saveDermatologist(dermatologistDto);
		var gson = new GsonBuilder().create();
		return new ResponseEntity<>(gson.toJson(review), HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/ratePharmacist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>ratePharmaceut(@RequestBody PharmacistDTO pharmacistDto){
		ratingService.savePharmacist(pharmacistDto);
		var gson = new GsonBuilder().create();
		return new ResponseEntity<>(gson.toJson(review), HttpStatus.OK);
	}
	
	@PutMapping(value = "/ratePharmacy", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>ratePharmacy(@RequestBody PharmacyDTO pharmacyDto){
		
		ratingService.savePharmacy(pharmacyDto);
		
		var gson = new GsonBuilder().create();
		return new ResponseEntity<>(gson.toJson(review), HttpStatus.OK);
	}
	@PutMapping(value = "/rateMedicine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>rateMedicine(@RequestBody MedicineDTO medicineDto){
		ratingService.saveMedicine(medicineDto);
		var gson = new GsonBuilder().create();
		return new ResponseEntity<>(gson.toJson(review), HttpStatus.OK);
	}
}