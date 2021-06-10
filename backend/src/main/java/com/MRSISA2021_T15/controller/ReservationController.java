package com.MRSISA2021_T15.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.model.Absence;
import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.Reservation;
import com.MRSISA2021_T15.service.ReservationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
	@Autowired
	ReservationService service;

	@GetMapping(path="/get/reservation={id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public @ResponseBody List<List<Object>> getAppointmentId(@PathVariable("id") Integer id) {
		return service.getReservations(id);
	}
	
	@PostMapping(path="/post",  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public @ResponseBody ResponseEntity<String> createAbsencePharmacist(@RequestBody Reservation reservation) {
		String message = service.giveOut(reservation);
		Gson gson = new GsonBuilder().create();
		if (message == "") {
			return new ResponseEntity<String>(gson.toJson("Medicine succesfully given out."), HttpStatus.OK);
		}
		return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
