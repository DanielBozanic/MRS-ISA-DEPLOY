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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@RestController
@RequestMapping("/complaint")
public class ComplaintController {
	@Autowired
	ComplaintService service;
	@Autowired
	AppointmentService service2;
	@Autowired
	PatientService service3;
	
	private final String inputComplaint = "You can input complaint";
	private final String sent = "Your complaint is sent. Thank you for your words.";
	
	
	@GetMapping(value = "/getAllDermatologists", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	List<Dermatologist> getAllDermatologists(){
		return service.findAllDermatologist();
	}
	
	
	@GetMapping(value = "/getAllPharmacist", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	List<Pharmacist> getAllPharmacist(){
		return service.findAllPharmacist();
	}
	
	
	@GetMapping(value = "/getAllPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	List<Pharmacy> getAllPharmacy(){
		return service.findAllPharmacy();
	}
	
	
	/*
	
	@GetMapping(value = "/getdermatologicalComplaint/{patientUsername}", produces = MediaType.APPLICATION_JSON_VALUE)
	List<ComplaintDermatologist>getdermatologicalComplaint(@PathVariable("patientUsername") String patientUsername){
		List<ComplaintDermatologist> complaints =  service.findAllDerC();
		List<ComplaintDermatologist> list = new ArrayList<ComplaintDermatologist>();
		for(ComplaintDermatologist d : complaints) {
			if(d.getPatient().getUsername().equals(patientUsername)) {
				list.add(d);
			}
		}
		return list;
	}
	
	
	@GetMapping(value = "/getpharmacistComplaint/{patientUsername}", produces = MediaType.APPLICATION_JSON_VALUE)
	List<ComplaintPharmacist>getpharmacistComplaint(@PathVariable("patientUsername") String patientUsername){
		List<ComplaintPharmacist> complaints =  service.findAllPhaC();
		List<ComplaintPharmacist> list = new ArrayList<ComplaintPharmacist>();
		for(ComplaintPharmacist d : complaints) {
			if(d.getPatient().getUsername().equals(patientUsername)) {
				list.add(d);
			}
		}
		return list;
	}
	
	@GetMapping(value = "/getpharmacyComplaint/{patientUsername}", produces = MediaType.APPLICATION_JSON_VALUE)
	List<ComplaintPharmacy>getpharmacyComplaint(@PathVariable("patientUsername") String patientUsername){
		List<ComplaintPharmacy> complaints =  service.findAllPhaYC();
		List<ComplaintPharmacy> list = new ArrayList<ComplaintPharmacy>();
		for(ComplaintPharmacy d : complaints) {
			if(d.getPatient().getName().equals(patientUsername)) {
				list.add(d);
			}
		}
		return list;
	}
	*/
	@GetMapping(value = "/getComplaints", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	List<Complaint>getpharmacyComplaint(){
		
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Complaint> list = new ArrayList<Complaint>();
		List<Complaint> complaints = service.findAll();
		
		for(Complaint c : complaints) {
			if(c.getPatient().getUsername().equals(p.getUsername())) {
				list.add(c);
			}
		}
		
		Complaint c = new Complaint();
		c.setId(0);
		if(list.isEmpty()) {
			list.add(c);
		}
		
		return list;
	}
	
	
	
	
	@PutMapping(value = "/checkDermatologist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>checkDermatologist(@RequestBody DermatologistDTO dermatologistDto){
		String message = "";
		boolean found = false;
		
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Patient>patients = service2.findAllDAwithPatientId(p.getId(), dermatologistDto.getId());
		
		if(!patients.isEmpty()) {
			found = true;
		}
		
		Gson gson = new GsonBuilder().create();
		if(found) {
			return new ResponseEntity<String>(gson.toJson(inputComplaint), HttpStatus.OK);
		}else {
			message = "Patient didn't have an appointment with this dermatologist.";
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@PutMapping(value = "/checkPharmacist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>checkPharmacist(@RequestBody PharmacistDTO pharmacistDto){
		String message = "";
		boolean found = false;
		
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Patient>patients = service2.findAllPAwithPatientId(p.getId(), pharmacistDto.getId());
		
		
		if(!patients.isEmpty()) {
			found = true;
		}
		
		Gson gson = new GsonBuilder().create();
		if(found) {
			return new ResponseEntity<String>(gson.toJson(inputComplaint), HttpStatus.OK);
		}else {
			message = "Patient didn't have an appointment with this pharmacist.";
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	@PutMapping(value = "/checkPharmacy", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>checkPharmacy(@RequestBody PharmacyDTO pharmacyDto){
		String message = "";
		boolean found = false;
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<Appointment>appos = service2.findAllPatientsId(p.getId());
		if(!appos.isEmpty()) {
			for(Appointment a : appos) {
				if(a.getPharmacy().getId().equals(pharmacyDto.getId())) {
					found = true;
				}
			}
		}
		
		Gson gson = new GsonBuilder().create();
		if(found) {
			return new ResponseEntity<String>(gson.toJson(inputComplaint), HttpStatus.OK);
		}else {
			message = "Patient didn't have an appointment in this pharmacy.";
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	@PostMapping(value = "/addComplaintToDermatologist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String> addComplaintToDermatologist(@RequestBody ComplaintDermatologistDTO complaintDto){
		System.out.print(complaintDto.getText());
		
		String message = "";
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ComplaintDermatologist complaint = new ComplaintDermatologist();
		complaint.setText(complaintDto.getText());
		complaint.setPatient(p);
		
		boolean found = false;
		
		Dermatologist derma = service3.findDermatologistWithId(complaintDto.getDermatologist().getId());
		if (derma!=null) {
			found = true;
			complaint.setDermatologist(derma);
			service.addDerComplaint(complaint);
		}
		
		if(found == false) {
			message = "Your complaint could not be saved.";
		}
		
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson(sent), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	
	//@PostMapping(value = "/addComplaintToPharmacist/{patientUsername}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value = "/addComplaintToPharmacist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String> addComplaintToPharmacist(@RequestBody ComplaintPharmacistDTO complaintDto){
		
		
		String message = "";
		Patient pa = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ComplaintPharmacist complaint = new ComplaintPharmacist();
		complaint.setText(complaintDto.getText());
		complaint.setPatient(pa);
		
		boolean found = false;
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
		
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson(sent), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping(value = "/addComplaintToPharmacy", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String> addComplaintToPharmacy(@RequestBody ComplaintPharmacyDTO complaintDto){
		
		String message = "";
		Patient pa = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ComplaintPharmacy complaint = new ComplaintPharmacy();
		complaint.setText(complaintDto.getText());
		complaint.setPatient(pa);
		
		boolean found = false;
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
		
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson(sent), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
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
		String message = service.sendResponse(responseDto);
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson("Response has been sent successully."), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
