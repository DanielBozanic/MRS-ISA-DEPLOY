package com.MRSISA2021_T15.controller;

import com.MRSISA2021_T15.dto.AppointmentEnd;
import com.MRSISA2021_T15.dto.AppointmentEndDermatologist;
import com.MRSISA2021_T15.model.*;
import com.MRSISA2021_T15.repository.AppointmentRepository;
import com.MRSISA2021_T15.service.AppointmentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment_creation")
public class AppointmentController {

	@Autowired
	private AppointmentService service;
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@PostMapping(path="/pharmacist",  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public @ResponseBody ResponseEntity<String> makeAppointmentPharmacist(@RequestBody AppointmentPharmacist appointment) {
		String message = service.makeAppointmentPharmacist(appointment);
		Gson gson = new GsonBuilder().create();
		if (message == "") {
			return new ResponseEntity<String>(gson.toJson("Appointment succesfully created."), HttpStatus.OK);
		}
		return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(path="/dermatologist",  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public @ResponseBody ResponseEntity<String> makeDermatologist(@RequestBody AppointmentDermatologist appointment) {
		String message = service.makeAppointmentDermatologist(appointment);
		Gson gson = new GsonBuilder().create();
		if (message == "") {
			return new ResponseEntity<String>(gson.toJson("Appointment succesfully created."), HttpStatus.OK);
		}
		return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping(path="/dermatologistPredefined/{appointmentId}",  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public @ResponseBody ResponseEntity<String> makeDermatologistPredefined(@PathVariable("appointmentId") Integer id, @RequestBody Patient patient) {
		String message = service.makeAppointmentDermatologistPredefined(id, patient);
		Gson gson = new GsonBuilder().create();
		if (message == "") {
			return new ResponseEntity<String>(gson.toJson("Appointment succesfully assigned to patient."), HttpStatus.OK);
		}
		return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(path="/getPharmacist/id={id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public @ResponseBody Optional<Appointment> getAppointmentId(@PathVariable("id") Integer id) {
		return service.findAllAppointmentsId(id);
	}
	
	@GetMapping(path="/getDermatologist/id={id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public @ResponseBody Optional<Appointment> getAppointmentDermatologistId(@PathVariable("id") Integer id) {
		return service.findAllAppointmentsId(id);
	}
	
	@PutMapping(path="/setDonePharmacist",  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public void makeDone(@RequestBody AppointmentPharmacist appointment) {
		service.makeTrue(appointment);
	}
	
	@PutMapping(path="/setDoneDermatologist",  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public void makeDoneDermatologist(@RequestBody AppointmentDermatologist appointment) {
		service.makeTrue(appointment);
	}
	
	@PostMapping(path="/endAppointmentPharmacist",  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public @ResponseBody ResponseEntity<String> endAppointmentPharmacist(@RequestBody AppointmentEnd appointment) {
		service.makeTrue(appointment.getAppointment());
		String message = service.endAppointment(appointment.getAppointment(), appointment.getMeds(), appointment.getComments());
		Gson gson = new GsonBuilder().create();
		if (message == "") {
			return new ResponseEntity<String>(gson.toJson("Appointment succesfully ended, information is saved."), HttpStatus.OK);
		}
		return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(path="/endAppointmentDermatologist",  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public @ResponseBody ResponseEntity<String> endAppointmentDermatologist(@RequestBody AppointmentEndDermatologist appointment) {
		service.makeTrue(appointment.getAppointment());
		String message = service.endAppointment(appointment.getAppointment(), appointment.getMeds(), appointment.getComments());
		Gson gson = new GsonBuilder().create();
		if (message == "") {
			return new ResponseEntity<String>(gson.toJson("Appointment succesfully ended, information is saved."), HttpStatus.OK);
		}
		return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(path="/employmentsDermatologist", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public @ResponseBody List<EmploymentDermatologist> getEmploymentsDermatologistId() {
		return service.employmentsDermatologist();
	}

	@PostMapping(path="/defineDermatologistAppointment")
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public ResponseEntity<String> defineDermatologistAppointment(@RequestBody AppointmentDermatologist ad) {
		List<Appointment> allAppointments = appointmentRepository.findAllDermatologistId(ad.getDermatologist().getId());
		boolean isValid = true;

		for (Appointment a : allAppointments){
			if(ad.getEnd().isAfter(a.getStart()) && ad.getStart().isBefore(a.getEnd()))
				isValid = false;
			else if (ad.getStart().isBefore(a.getEnd()) && ad.getEnd().isAfter(a.getStart()))
				isValid = false;
		}

		if(isValid)
			appointmentRepository.save(ad);
		return ResponseEntity.ok().build();
	}

	@GetMapping(path="/getPredefinedDermatologistAppointments/{pharmacyId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AppointmentDermatologist> getPredefinedDermatologistAppointments(@PathVariable Integer pharmacyId) {
		List<Appointment> appointmentList = appointmentRepository.findAll();
		List<Appointment> appointmentPharmacyList = new ArrayList<>();
		List returnList = new ArrayList<AppointmentDermatologist>();
		for ( Appointment a : appointmentList) {
			if (a.getPharmacy().getId() == pharmacyId) {
				appointmentPharmacyList.add(a);
			}
		}
		for (Appointment ap : appointmentPharmacyList){
			if (ap.getPatient() == null )
				returnList.add(ap);
		}
		return returnList;
	}


}
