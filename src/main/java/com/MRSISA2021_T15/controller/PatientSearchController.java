package com.MRSISA2021_T15.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.dto.AppointmentPatient;
import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.AppointmentDermatologist;
import com.MRSISA2021_T15.model.AppointmentPharmacist;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.service.CalendarService;
import com.MRSISA2021_T15.service.PatientSearchService;

@RestController
@RequestMapping("/patient-search")
public class PatientSearchController {
	@Autowired
	private PatientSearchService service;
	@Autowired
	private CalendarService calendars;

	@GetMapping(value = "/searchPatientsPharmacist/start={start}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public Collection<AppointmentPatient> searchPatientsPharmacist(@PathVariable("start") String start) {
		Pharmacist p = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ArrayList<AppointmentPatient> patients = new ArrayList<AppointmentPatient>();
		for (Patient patient : service.findAll()) {
			for (Appointment appointment : service.all()) {
				if (appointment instanceof AppointmentPharmacist) {
					AppointmentPharmacist ap = (AppointmentPharmacist) appointment;
					if (ap.getPatient().getId() == patient.getId() && ap.getPharmacist().getId() == p.getId() && ap.getPatient().getUsername().toLowerCase().startsWith(start.toLowerCase()) && ap.isDone()) {
						AppointmentPatient apa = new AppointmentPatient();
						apa.setPatient(patient);
						apa.setDate(ap.getStart());
						patients.add(apa);
						break;
					}
				}
			}
		}

		return patients;
	}
	
	@GetMapping(value = "/searchAllPharmacist/name={name}surname={surname}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public Collection<Patient> searchAllPharmacist(@PathVariable("name") String name, @PathVariable("surname") String surname) {
		ArrayList<Patient> patients = new ArrayList<>();
		for (Patient patient : service.allPatients()) {
			if(patient.getName().toLowerCase().startsWith(name.toLowerCase()) && patient.getSurname().toLowerCase().startsWith(surname.toLowerCase())) {
				patients.add(patient);
			}
		}
		return patients;
	}
	
	@GetMapping(value = "/searchAppointmentsPharmacist/name={name}surname={surname}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public Collection<Appointment> searchAppointmentsPharmacist(@PathVariable("name") String name, @PathVariable("surname") String surname) {
		Pharmacist p = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ArrayList<Appointment> appointments = new ArrayList<>();
		for(Appointment appointment : calendars.findAllPharmacistToday(p.getId())) {
			if(appointment.getPatient() != null) {
				if(appointment.getPatient().getName().toLowerCase().startsWith(name.toLowerCase()) && appointment.getPatient().getSurname().toLowerCase().startsWith(surname.toLowerCase())) {
					appointments.add(appointment);
				}
			}
		}
		return appointments;
	}
	
	@GetMapping(value = "/searchAllDermatologist/name={name}surname={surname}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public Collection<Patient> searchAllDermatologist(@PathVariable("name") String name, @PathVariable("surname") String surname) {
		ArrayList<Patient> patients = new ArrayList<>();
		for (Patient patient : service.allPatients()) {
			if(patient.getName().toLowerCase().startsWith(name) && patient.getSurname().toLowerCase().startsWith(surname)) {
				patients.add(patient);
			}
		}
		return patients;
	}
	
	@GetMapping(value = "/searchAppointmentsDermatologist/name={name}surname={surname}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public Collection<Appointment> searchAppointmentsDermatologist(@PathVariable("name") String name, @PathVariable("surname") String surname) {
		Dermatologist p = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ArrayList<Appointment> appointments = new ArrayList<>();
		for(Appointment appointment : calendars.findAllDermatologistToday(p.getId())) {
			if(appointment.getPatient() != null) {
				if(appointment.getPatient().getName().toLowerCase().startsWith(name.toLowerCase()) && appointment.getPatient().getSurname().toLowerCase().startsWith(surname.toLowerCase())) {
					appointments.add(appointment);
				}
			}
		}
		return appointments;
	}

	@GetMapping(value = "/searchPatientsDermatologist/start={start}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public Collection<AppointmentPatient> searchPatientsDermatolog(@PathVariable("start") String start) {
		Dermatologist d = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ArrayList<AppointmentPatient> patients = new ArrayList<AppointmentPatient>();
		for (Patient patient : service.findAll()) {
			for (Appointment appointment : service.all()) {
				if (appointment instanceof AppointmentDermatologist) {
					AppointmentDermatologist ap = (AppointmentDermatologist) appointment;
					if (ap.getPatient() != null) {
						if (ap.getPatient().getId() == patient.getId() && ap.getDermatologist().getId() == d.getId()  && ap.getPatient().getUsername().toLowerCase().startsWith(start.toLowerCase())  && ap.isDone()) {
							AppointmentPatient apa = new AppointmentPatient();
							apa.setPatient(patient);
							apa.setDate(ap.getStart());
							patients.add(apa);
							break;
						}
					}
				}
			}
		}

		return patients;
	}

}
