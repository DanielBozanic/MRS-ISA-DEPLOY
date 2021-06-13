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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.dto.AppointmentPharmacistDTO;
import com.MRSISA2021_T15.model.AppointmentPharmacist;
import com.MRSISA2021_T15.model.CanceledPharAppoinment;
import com.MRSISA2021_T15.model.EmploymentPharmacist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.service.AppointmentService;
import com.MRSISA2021_T15.service.CanceledAppService;
import com.MRSISA2021_T15.service.FarAppPatientService;
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
	
	private String cancel = "You can't cancel your appointment under 24h before it's beginning!";
	
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
	public ResponseEntity<String> newAppointment(@RequestBody AppointmentPharmacistDTO appointmentDto){
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var appointment = new AppointmentPharmacist();
		appointment.setDiscount(appointmentDto.getDiscount());
		appointment.setStart(appointmentDto.getStart());
		appointment.setEnd(appointmentDto.getEnd());
		appointment.setPharmacist(appointmentDto.getPharmacist());
		appointment.setPrice(appointmentDto.getPrice());
		appointment.setPatient(patient);
		serviceApp.newPharmaciesApp(appointment);
		var gson = new GsonBuilder().create();
		return new ResponseEntity<>(gson.toJson("You made an appoinment with a pharmacist. Thank you for the trust!"), HttpStatus.OK);
	}
	
	
	
	
	
	@PutMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String> delete(@RequestBody AppointmentPharmacistDTO appointmentDto){
		
		var message = "";
		var now = LocalDateTime.now();
		if(now.getYear() == appointmentDto.getStart().getYear()) {
			if(now.getMonthValue() == appointmentDto.getStart().getMonthValue()) {
				if(now.getDayOfMonth() == appointmentDto.getStart().getDayOfMonth()) {
					message = cancel;
				}else if(now.getDayOfMonth() + 1 == appointmentDto.getStart().getDayOfMonth()) { //ako je otkazujem dan prije
					//provjeri sate i minute onda
					if(now.getHour() > appointmentDto.getStart().getHour()) {
						message = cancel;
					}else if(now.getHour() == appointmentDto.getStart().getHour()) {
						//ovdje provjeri minute
						if(now.getMinute() >  appointmentDto.getStart().getMinute()) { //moze tacno 24 od pocetka da otkaze
							message = cancel;
						}
					}
				}
			}
		}
		
		if(message.equals("")) {
			serviceApp.deletePharmaciestApp(appointmentDto);
		}
		
		var gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<>(gson.toJson("You canceled your appointment with pharmacist!"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
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
