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

import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.service.RatingService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/rating")
public class RatingController {
	@Autowired
	RatingService ratingService;
	

	@GetMapping(value = "/getDermatologistToRate", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	List<Dermatologist> getAllDermatologists(){
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ratingService.findAllDoneDerAppOfPatient(p);
	}
	
	
	@GetMapping(value = "/getPharmacistsToRate", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	List<Pharmacist> getPharmacistsToRate(){
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ratingService.findAllDonePharAppOfPatient(p);
	}
	
	
	@GetMapping(value = "/getPharmaciesToRate", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	List<Pharmacy> getPharmaciesToRate(){
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ratingService.findAllPharmaciesThatPatientHadApp(p);
	}
	
	@GetMapping(value = "/getMedicineToRate", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	List<Medicine> getMedicineToRate(){
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ratingService.findAllMedicinesThatPatientCanRate(p);
	}
	
	
	
	@GetMapping(value = "/getPenalties", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	Patient getPenalties(){
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return p;
	}
	
	
	
	@PutMapping(value = "/rateDermatologist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>rateDermatologist(@RequestBody Dermatologist dermatologist){
		
		ratingService.saveDermatologist(dermatologist);
		
		Gson gson = new GsonBuilder().create();
		return new ResponseEntity<String>(gson.toJson("Thank you for your review."), HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/ratePharmacist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>ratePharmaceut(@RequestBody Pharmacist pharmacist){
		
		ratingService.savePharmacist(pharmacist);
		
		Gson gson = new GsonBuilder().create();
		return new ResponseEntity<String>(gson.toJson("Thank you for your review."), HttpStatus.OK);
	}
	
	
	
	@PutMapping(value = "/ratePharmacy", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>ratePharmacy(@RequestBody Pharmacy pharmacy){
		
		ratingService.savePharmacy(pharmacy);
		
		Gson gson = new GsonBuilder().create();
		return new ResponseEntity<String>(gson.toJson("Thank you for your review."), HttpStatus.OK);
	}
	
	
	
	@PutMapping(value = "/rateMedicine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>rateMedicine(@RequestBody Medicine medicine){
		
		ratingService.saveMedicine(medicine);
		
		Gson gson = new GsonBuilder().create();
		return new ResponseEntity<String>(gson.toJson("Thank you for your review."), HttpStatus.OK);
	}
	
	
	
	
}

