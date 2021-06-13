package com.MRSISA2021_T15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.dto.AppointmentConsultationPointsDTO;
import com.MRSISA2021_T15.dto.CategoryDTO;
import com.MRSISA2021_T15.model.CategoryName;
import com.MRSISA2021_T15.service.LoyaltyProgramService;

@RestController
@RequestMapping("/loyaltyProgram")
public class LoyaltyProgramController {
	
	@Autowired
	private LoyaltyProgramService loyaltyProgramService;
	
	@PostMapping(value = "/defineCategories", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public ResponseEntity<String> defineCategories(@RequestBody CategoryDTO categoryDto) {
		return loyaltyProgramService.defineCategories(categoryDto);
	}
	
	@GetMapping(value = "/getCategoryNames", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public CategoryName[] getCategoryNames() {
		return loyaltyProgramService.getCategoryNames();
	}
	
	@PostMapping(value = "/definePointsForAppointmentAndConsulation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public ResponseEntity<String> definePointsForAppointmentAndConsulation(@RequestBody AppointmentConsultationPointsDTO acpDto) {
		return loyaltyProgramService.definePointsForAppointmentAndConsulation(acpDto);
	}
}