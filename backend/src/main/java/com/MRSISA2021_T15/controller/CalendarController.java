package com.MRSISA2021_T15.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Event;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.service.CalendarService;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

	@Autowired
	private CalendarService service;
	
	@GetMapping(value = "/calendarPharmacist", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public Collection<Event> calendarPharmacist(){
		Pharmacist p = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Appointment> appointments = service.findAllPharmacist(p.getId());
		ArrayList<Event> events = new ArrayList<Event>();
		for (Appointment appointment : appointments) {
			Event event = new Event();
			event.setTitle("Appointment with" + " " + appointment.getPatient().getSurname() + " " +  appointment.getPatient().getName());
			event.setStart(appointment.getStart());
			event.setEnd(appointment.getEnd());
			events.add(event);
		}
		return events;
	}
	
	@GetMapping(value = "/calendarPharmacistToday", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public Collection<Event> calendarPharmacistToday(){
		Pharmacist p = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Appointment> appointments = service.findAllPharmacistToday(p.getId());
		ArrayList<Event> events = new ArrayList<Event>();
		for (Appointment appointment : appointments) {
			Event event = new Event();
			event.setId(appointment.getId());
			event.setTitle("Appointment with" + " " + appointment.getPatient().getSurname() + " " +  appointment.getPatient().getName());
			event.setStart(appointment.getStart());
			event.setEnd(appointment.getEnd());
			events.add(event);
		}
		return events;
	}
	
	@GetMapping(value = "/calendarDermatologist/pharmacy={pharmacyId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public Collection<Event> calendarDermatologist(@PathVariable("pharmacyId") Integer pharmacyId){
		Dermatologist d = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Appointment> appointments = service.findAllDermatologist(d.getId());
		ArrayList<Event> events = new ArrayList<Event>();
		for (Appointment appointment : appointments) {
			if(appointment.getPharmacy().getId() == pharmacyId) {
				Event event = new Event();
				if(appointment.getPatient() == null) {
					event.setTitle("Predefined Appointment in " + appointment.getPharmacy().getName());
				}else {
					event.setTitle("Appointment with " + appointment.getPatient().getSurname() + " " +  appointment.getPatient().getName() + " in " + appointment.getPharmacy().getName());
				}
				event.setStart(appointment.getStart());
				event.setEnd(appointment.getEnd());
				events.add(event);
			}
		}
		return events;
	}
	
	@GetMapping(value = "/calendarDermatologist", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public Collection<Event> calendarDermatologist(){
		Dermatologist d = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Appointment> appointments = service.findAllDermatologist(d.getId());
		ArrayList<Event> events = new ArrayList<Event>();
		for (Appointment appointment : appointments) {
				Event event = new Event();
				if(appointment.getPatient() == null) {
					event.setTitle("Predefined Appointment in " + appointment.getPharmacy().getName());
				}else {
					event.setTitle("Appointment with " + appointment.getPatient().getSurname() + " " +  appointment.getPatient().getName() + " in " + appointment.getPharmacy().getName());
				}
				event.setStart(appointment.getStart());
				event.setEnd(appointment.getEnd());
				events.add(event);
		}
		return events;
	}
	
	@GetMapping(value = "/calendarDermatologistToday", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public Collection<Event> calendarDermatologistToday(){
		Dermatologist d = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Appointment> appointments = service.findAllDermatologistToday(d.getId());
		ArrayList<Event> events = new ArrayList<Event>();
		for (Appointment appointment : appointments) {
				Event event = new Event();
				if(appointment.getPatient() == null) {
					continue;
				}else {
					event.setTitle("Appointment with " + appointment.getPatient().getSurname() + " " +  appointment.getPatient().getName() + " in " + appointment.getPharmacy().getName());
				}
				event.setId(appointment.getId());
				event.setStart(appointment.getStart());
				event.setEnd(appointment.getEnd());
				events.add(event);
		}
		return events;
	}
	
	@GetMapping(value = "/calendarDermatologistPredefined", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public Collection<Event> calendarDermatologistPredefined(){
		Dermatologist d = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Appointment> appointments = service.findAllDermatologist(d.getId());
		ArrayList<Event> events = new ArrayList<Event>();
		for (Appointment appointment : appointments) {
				if(appointment.getPatient() == null) {
					Event event = new Event();
					event.setTitle("Predefined Appointment in " + appointment.getPharmacy().getName());
					event.setStart(appointment.getStart());
					event.setEnd(appointment.getEnd());
					events.add(event);
				}
		}
		return events;
	}
	
	@GetMapping(value = "/calendarDermatologistPredefined/pharmacy={pId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public Collection<Event> calendarDermatologistPredefined(@PathVariable("pId") Integer pharmacyId){
		Dermatologist d = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Appointment> appointments = service.findAllDermatologist(d.getId());
		ArrayList<Event> events = new ArrayList<Event>();
		for (Appointment appointment : appointments) {
			if(appointment.getPharmacy().getId() == pharmacyId && appointment.getPatient() == null) {
				Event event = new Event();
				event.setId(appointment.getId());
				event.setTitle("Predefined Appointment in " + appointment.getPharmacy().getName());
				event.setStart(appointment.getStart());
				event.setEnd(appointment.getEnd());
				events.add(event);
			}
		}
		return events;
	}
}
