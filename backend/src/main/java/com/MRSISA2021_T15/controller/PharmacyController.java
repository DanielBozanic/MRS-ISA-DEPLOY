package com.MRSISA2021_T15.controller;

import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.repository.EmploymentRepository;
import com.MRSISA2021_T15.repository.PharmacyRepository;
import com.MRSISA2021_T15.service.PharmacyService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {

	@Autowired
	private PharmacyService pharmacyService;

	@Autowired
	private EmploymentRepository empRepo;

	@Autowired
	private PharmacyRepository pharmacyRepository;

	@PostMapping(value = "/registerPharmacy", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public ResponseEntity<String> registerPharmacy(@RequestBody Pharmacy pharmacy) {
		String message = pharmacyService.registerPharmacy(pharmacy);
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson("The pharmacy has been registered successfully."), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getPharmacyForPharmacist", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public Pharmacy getPharmacyforPharmacist() {
		Pharmacist p = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return empRepo.findByPharmacistId(p.getId()).getPharmacy();
	}
	
	@GetMapping(value = "/getPharmacies", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public List<Pharmacy> getPharmacies() {
		return pharmacyService.getPharmacies();
	}
	
	//@GetMapping(value = "/getPharmacyForDermatologist", produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	//public Pharmacy getPharmacyforDermatologist() {
	//	Pharmacist p = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	//	return empRepo.findByPharmacistId(p.getId()).getPharmacy();
	//}

	@GetMapping(value = "/getPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
	public Pharmacy getPharmacy(@RequestBody Pharmacy pharmacy){
		return pharmacyRepository.findPharmacyWithId(pharmacy.getId());
	}

	@GetMapping(value = "/publicGetPharmacies", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pharmacy> publicGetPharmacies() {
		return pharmacyService.getPharmacies();
	}


}
