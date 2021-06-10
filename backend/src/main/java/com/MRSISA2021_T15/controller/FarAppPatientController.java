package com.MRSISA2021_T15.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.model.AppointmentDermatologist;
import com.MRSISA2021_T15.model.AppointmentPharmacist;
import com.MRSISA2021_T15.model.CanceledPharAppoinment;
import com.MRSISA2021_T15.model.ComplaintDermatologist;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.EmploymentPharmacist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.service.AppointmentService;
import com.MRSISA2021_T15.service.CanceledAppService;
import com.MRSISA2021_T15.service.FarAppPatientService;
import com.MRSISA2021_T15.service.PatientService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/make_farmaceut_appointment")
public class FarAppPatientController {
	@Autowired
	FarAppPatientService service;
	
	@Autowired
	AppointmentService serviceApp;
	
	@Autowired
	CanceledAppService cancelS;
	
	
	
	@GetMapping(value = "/getAllCanceledApp", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	List<CanceledPharAppoinment> getAllCanceledApp(){
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<CanceledPharAppoinment>app = cancelS.getPatientAllCanceledApp(patient);
		return app;
	}
	
	@GetMapping(value = "/getAllEmploymentPharmacist", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	List<EmploymentPharmacist> getAllEmploymentPharmacist(){
		return service.findAllPharmacist();
	}
	
	
	
	@GetMapping(value = "/getAllAppointment", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	List<AppointmentPharmacist> getAllPharmacistApp(){
		return serviceApp.getPharmacisApp();
	}
	
	
	
	@PostMapping(value = "/newAppointment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String> newAppointment(@RequestBody AppointmentPharmacist appointment){
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		appointment.setPatient(patient);
		serviceApp.newPharmaciesApp(appointment);
		Gson gson = new GsonBuilder().create();
		return new ResponseEntity<String>(gson.toJson("You made an appoinment with a pharmaciest. Thank you for the trust!"), HttpStatus.OK);
	}
	
	
	
	
	
	@PutMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String> delete(@RequestBody AppointmentPharmacist appointment){
		
		String message = "";
		LocalDateTime now = LocalDateTime.now();
		if(now.getYear() == appointment.getStart().getYear()) {
			if(now.getMonthValue() == appointment.getStart().getMonthValue()) {
				if(now.getDayOfMonth() == appointment.getStart().getDayOfMonth()) {
					message = "You can't cancel your appointment under 24h before it's beggining!";
				}else if(now.getDayOfMonth() + 1 == appointment.getStart().getDayOfMonth()) { //ako je otkazujem dan prije
					//provjeri sate i minute onda
					if(now.getHour() > appointment.getStart().getHour()) {
						message = "You can't cancel your appointment under 24h before it's beggining!";
					}else if(now.getHour() == appointment.getStart().getHour()) {
						//ovdje provjeri minute
						if(now.getMinute() >  appointment.getStart().getMinute()) { //moze tacno 24 od pocetka da otkaze
							message = "You can't cancel your appointment under 24h before it's beggining!";
						}
					}
				}
			}
		}
		
		if(message.equals("")) {
			serviceApp.deletePharmaciestApp(appointment);
		}
		
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson("You canceled your appointment with pharmacist!"), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	@GetMapping(value = "/getPatientPharApp", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public List<AppointmentPharmacist>getPatientDerApp(){
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return serviceApp.findAllPharAppWithPatientId(p.getId());
	}
	
	
	@GetMapping(value = "/getPastPatientPharApp", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public List<AppointmentPharmacist>getPastPatientDerApp(){
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return serviceApp.findAllPastPharAppWithPatientId(p.getId());
	}
	
	
}
