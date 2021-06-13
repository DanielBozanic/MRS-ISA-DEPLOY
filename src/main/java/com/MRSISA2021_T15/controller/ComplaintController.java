package com.MRSISA2021_T15.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.model.Complaint;
import com.MRSISA2021_T15.dto.ComplaintDTO;
import com.MRSISA2021_T15.dto.ComplaintDermatologistDTO;
import com.MRSISA2021_T15.dto.ComplaintPharmacistDTO;
import com.MRSISA2021_T15.dto.ComplaintPharmacyDTO;
import com.MRSISA2021_T15.dto.DermatologistDTO;
import com.MRSISA2021_T15.dto.PharmacistDTO;
import com.MRSISA2021_T15.dto.PharmacyDTO;
import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.ComplaintDermatologist;
import com.MRSISA2021_T15.model.ComplaintPharmacist;
import com.MRSISA2021_T15.model.ComplaintPharmacy;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.service.AppointmentService;
import com.MRSISA2021_T15.service.ComplaintService;
import com.MRSISA2021_T15.service.PatientService;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {
	
	@Autowired
	private ComplaintService service;
	@Autowired
	private AppointmentService service2;
	@Autowired
	private PatientService service3;
	
	private final String inputComplaint = "You can input complaint";
	private final String sent = "Your complaint is sent. Thank you for your words.";
	
	@GetMapping(value = "/getAllDermatologists", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public List<Dermatologist> getAllDermatologists(){
		return service.findAllDermatologist();
	}
	
	@GetMapping(value = "/getAllPharmacist", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public List<Pharmacist> getAllPharmacist(){
		return service.findAllPharmacist();
	}
	
	@GetMapping(value = "/getAllPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public List<Pharmacy> getAllPharmacy(){
		return service.findAllPharmacy();
	}
	
	@GetMapping(value = "/getComplaints", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	List<Complaint>getpharmacyComplaint(){
		
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Complaint> list = new ArrayList<>();
		List<Complaint> complaints = service.findAll();
		
		for(Complaint c : complaints) {
			if(c.getPatient().getUsername().equals(p.getUsername())) {
				list.add(c);
			}
		}
		
		var c = new Complaint();
		c.setId(0);
		if(list.isEmpty()) {
			list.add(c);
		}
		
		return list;
	}
	
	@PutMapping(value = "/checkDermatologist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>checkDermatologist(@RequestBody DermatologistDTO dermatologistDto){
		var message = "";
		var found = false;
		
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Patient>patients = service2.findAllDAwithPatientId(p.getId(), dermatologistDto.getId());
		
		if(!patients.isEmpty()) {
			found = true;
		}
		
		var gson = new GsonBuilder().create();
		if(found) {
			return new ResponseEntity<>(gson.toJson(inputComplaint), HttpStatus.OK);
		}else {
			message = "Patient didn't have an appointment with this dermatologist.";
			return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@PutMapping(value = "/checkPharmacist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>checkPharmacist(@RequestBody PharmacistDTO pharmacistDto){
		var message = "";
		var found = false;
		
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Patient>patients = service2.findAllPAwithPatientId(p.getId(), pharmacistDto.getId());
		
		
		if(!patients.isEmpty()) {
			found = true;
		}
		
		var gson = new GsonBuilder().create();
		if(found) {
			return new ResponseEntity<>(gson.toJson(inputComplaint), HttpStatus.OK);
		}else {
			message = "Patient didn't have an appointment with this pharmacist.";
			return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	@PutMapping(value = "/checkPharmacy", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>checkPharmacy(@RequestBody PharmacyDTO pharmacyDto){
		var message = "";
		var found = false;
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<Appointment>appos = service2.findAllPatientsId(p.getId());
		if(!appos.isEmpty()) {
			for(Appointment a : appos) {
				if(a.getPharmacy().getId().equals(pharmacyDto.getId())) {
					found = true;
				}
			}
		}
		
		var gson = new GsonBuilder().create();
		if(found) {
			return new ResponseEntity<>(gson.toJson(inputComplaint), HttpStatus.OK);
		}else {
			message = "Patient didn't have an appointment in this pharmacy.";
			return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	@PostMapping(value = "/addComplaintToDermatologist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String> addComplaintToDermatologist(@RequestBody ComplaintDermatologistDTO complaintDto){
		var message = "";
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var complaint = new ComplaintDermatologist();
		complaint.setText(complaintDto.getText());
		complaint.setPatient(p);
		
		var found = false;
		
		var derma = service3.findDermatologistWithId(complaintDto.getDermatologist().getId());
		if (derma!=null) {
			found = true;
			complaint.setDermatologist(derma);
			service.addDerComplaint(complaint);
		}
		
		if(found == false) {
			message = "Your complaint could not be saved.";
		}
		
		var gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<>(gson.toJson(sent), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@PostMapping(value = "/addComplaintToPharmacist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String> addComplaintToPharmacist(@RequestBody ComplaintPharmacistDTO complaintDto){
		var message = "";
		Patient pa = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var complaint = new ComplaintPharmacist();
		complaint.setText(complaintDto.getText());
		complaint.setPatient(pa);
		
		var found = false;
		List<Pharmacist> pharmacists = service3.findAllPharmacist();
		for(Pharmacist p : pharmacists) {
			if(p.getId().equals(complaint.getPharmacist().getId())) {
				complaint.setPharmacist(p);
				service.addPhaComplaint(complaint);
				found = true;
			}
		}
		
		if(found == false) {
			message = "Something went wrong";
		}
		
		var gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<>(gson.toJson(sent), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping(value = "/addComplaintToPharmacy", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String> addComplaintToPharmacy(@RequestBody ComplaintPharmacyDTO complaintDto){
		var message = "";
		Patient pa = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var complaint = new ComplaintPharmacy();
		complaint.setText(complaintDto.getText());
		complaint.setPatient(pa);
		
		var found = false;
		List<Pharmacy> pharmacies = service.findAllPharmacy();
		for(Pharmacy p : pharmacies) {
			if(p.getId().equals(complaint.getPharmacy().getId())) {
				complaint.setPharmacy(p);
				service.addPharyComplaint(complaint);
				found = true;
			}
		}
		
		if(found == false) {
			message = "Something went wrong";
		}
		
		var gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<>(gson.toJson(sent), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value = "/getComplaintsToRespond", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public List<Complaint> getComplaintsToRespond() {
		return service.getComplaintsToRespond();
	}
	
	@GetMapping(value = "/getResponses", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public List<Complaint> getResponses() {
		return service.getResponses();
	}
	
	@PutMapping(value = "/sendResponse", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public ResponseEntity<String> sendResponse(@RequestBody ComplaintDTO responseDto) {
		var message = service.sendResponse(responseDto);
		var gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<>(gson.toJson("Response has been sent successully."), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
