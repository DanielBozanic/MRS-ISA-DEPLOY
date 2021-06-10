package com.MRSISA2021_T15.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.model.EReceiptSearch;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.service.PatientService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.NotFoundException;

@RestController
@RequestMapping("/patients")
public class PatientController {
	
	@Autowired
	private PatientService service;
	
	@GetMapping(value = "/searchPatient", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public Patient searchPatients(){
		return (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	@PutMapping(value = "/changeData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String> changeData(@RequestBody Patient patient){
		String message = service.updatePatientData(patient);
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson("Update successfull."), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/updatePassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String> updatePassword(@RequestBody ChangePassword passwords) {
		String message = service.updatePassword(passwords);
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson(""), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/subscribeToPharamacy", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String> subscribeToPharamacy(@RequestBody Pharmacy pharmacy) {
		service.subscribeToPharamacy(pharmacy);
		Gson gson = new GsonBuilder().create();	
		return new ResponseEntity<String>(gson.toJson("Subscribed to pharmacy successfully."), HttpStatus.OK);
	}
	
	@PutMapping(value = "/unsubscribeToPharamacy", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String> unsubscribeToPharamacy(@RequestBody Pharmacy pharmacy) {
		service.unsubscribeToPharamacy(pharmacy);
		Gson gson = new GsonBuilder().create();	
		return new ResponseEntity<String>(gson.toJson("Unsubscribed from pharmacy successfully."), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSubscribedPharmacies", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public List<Pharmacy> getSubscribedPharmacies() {
		return service.getSubscribedPharmacies();
	}
	
	@PostMapping(value = "/sendQrCode", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public List<EReceiptSearch> sendQrCode(@RequestParam("qrCode") MultipartFile file) throws IOException, NotFoundException {
		return service.sendQrCode(file);
	}
	
	@PostMapping(value = "/issueEReceipt", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String> issueEReceipt(@RequestBody EReceiptSearch eReceiptSearch) {
		String message = service.issueEReceipt(eReceiptSearch);
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson("Medicine issued successfully."), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/getDiscountByPatientCategory", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public Integer getDiscountByPatientCategory() {
		return service.getDiscountByPatientCategory();
	}
}

