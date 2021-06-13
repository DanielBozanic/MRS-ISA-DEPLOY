package com.MRSISA2021_T15.controller;

import com.MRSISA2021_T15.dto.AppointmentDermatologistDTO;
import com.MRSISA2021_T15.dto.AppointmentEnd;
import com.MRSISA2021_T15.dto.AppointmentEndDermatologist;
import com.MRSISA2021_T15.dto.AppointmentPharmacistDTO;
import com.MRSISA2021_T15.dto.PatientDTO;
import com.MRSISA2021_T15.model.*;
import com.MRSISA2021_T15.repository.AppointmentRepository;
import com.MRSISA2021_T15.repository.UserRepository;
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
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping(path="/pharmacist",  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public @ResponseBody ResponseEntity<String> makeAppointmentPharmacist(@RequestBody AppointmentPharmacistDTO appointmentDto) {
		AppointmentPharmacist appointment = new AppointmentPharmacist();
		appointment.setDiscount(appointmentDto.getDiscount());
		appointment.setStart(appointmentDto.getStart());
		appointment.setEnd(appointmentDto.getEnd());
		appointment.setPatient(appointmentDto.getPatient());
		appointment.setPharmacist(appointmentDto.getPharmacist());
		appointment.setPrice(appointmentDto.getPrice());
		appointment.setPharmacy(appointmentDto.getPharmacy());
		String message = service.makeAppointmentPharmacist(appointment);
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<>(gson.toJson("Appointment succesfully created."), HttpStatus.OK);
		}
		return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(path="/dermatologist",  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public @ResponseBody ResponseEntity<String> makeDermatologist(@RequestBody AppointmentDermatologistDTO appointmentDto) {
		AppointmentDermatologist appointment = new AppointmentDermatologist();
		appointment.setDiscount(appointmentDto.getDiscount());
		appointment.setStart(appointmentDto.getStart());
		appointment.setEnd(appointmentDto.getEnd());
		appointment.setPatient(appointmentDto.getPatient());
		appointment.setDermatologist(appointmentDto.getDermatologist());
		appointment.setPrice(appointmentDto.getPrice());
		appointment.setPharmacy(appointmentDto.getPharmacy());
		String message = service.makeAppointmentDermatologist(appointment);
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<>(gson.toJson("Appointment succesfully created."), HttpStatus.OK);
		}
		return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping(path="/dermatologistPredefined/{appointmentId}",  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public @ResponseBody ResponseEntity<String> makeDermatologistPredefined(@PathVariable("appointmentId") Integer id, @RequestBody PatientDTO patientDto) {
		Patient patient = (Patient) userRepository.findById(patientDto.getId()).orElse(null);
		String message = "";
		if (patient != null) {
			message = service.makeAppointmentDermatologistPredefined(id, patient);
		}
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<>(gson.toJson("Appointment succesfully assigned to patient."), HttpStatus.OK);
		}
		return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
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
	public void makeDone(@RequestBody AppointmentPharmacistDTO appointmentDto) {
		AppointmentPharmacist appointment = (AppointmentPharmacist) appointmentRepository.findById(appointmentDto.getId()).orElse(null);
		if (appointment != null) {
			service.makeTrue(appointment);
		}
	}
	
	@PutMapping(path="/setDoneDermatologist",  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public void makeDoneDermatologist(@RequestBody AppointmentDermatologistDTO appointmentDto) {
		AppointmentDermatologist appointment = (AppointmentDermatologist) appointmentRepository.findById(appointmentDto.getId()).orElse(null);
		if (appointment != null) {
			service.makeTrue(appointment);
		}
	}
	
	@PostMapping(path="/endAppointmentPharmacist",  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public @ResponseBody ResponseEntity<String> endAppointmentPharmacist(@RequestBody AppointmentEnd appointment) {
		service.makeTrue(appointment.getAppointment());
		String message = service.endAppointment(appointment.getAppointment(), appointment.getMeds(), appointment.getComments());
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<>(gson.toJson("Appointment succesfully ended, information is saved."), HttpStatus.OK);
		}
		return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(path="/endAppointmentDermatologist",  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public @ResponseBody ResponseEntity<String> endAppointmentDermatologist(@RequestBody AppointmentEndDermatologist appointment) {
		service.makeTrue(appointment.getAppointment());
		String message = service.endAppointment(appointment.getAppointment(), appointment.getMeds(), appointment.getComments());
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<>(gson.toJson("Appointment succesfully ended, information is saved."), HttpStatus.OK);
		}
		return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(path="/employmentsDermatologist", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public @ResponseBody List<EmploymentDermatologist> getEmploymentsDermatologistId() {
		return service.employmentsDermatologist();
	}

	@PostMapping(path="/defineDermatologistAppointment")
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public ResponseEntity<String> defineDermatologistAppointment(@RequestBody AppointmentDermatologistDTO adDto) {
		AppointmentDermatologist ad = new AppointmentDermatologist();
		ad.setDiscount(adDto.getDiscount());
		ad.setStart(adDto.getStart());
		ad.setEnd(adDto.getEnd());
		ad.setPatient(adDto.getPatient());
		ad.setDermatologist(adDto.getDermatologist());
		ad.setPrice(adDto.getPrice());
		ad.setPharmacy(adDto.getPharmacy());
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
		List<AppointmentDermatologist> returnList = new ArrayList<AppointmentDermatologist>();
		for ( Appointment a : appointmentList) {
			if (a.getPharmacy().getId().equals(pharmacyId)) {
				appointmentPharmacyList.add(a);
			}
		}
		for (Appointment ap : appointmentPharmacyList){
			if (ap.getPatient() == null )
				returnList.add((AppointmentDermatologist)ap);
		}
		return returnList;
	}


}
