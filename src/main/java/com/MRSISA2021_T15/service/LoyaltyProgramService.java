package com.MRSISA2021_T15.service;

import org.springframework.http.ResponseEntity;

import com.MRSISA2021_T15.dto.AppointmentConsultationPointsDTO;
import com.MRSISA2021_T15.dto.CategoryDTO;
import com.MRSISA2021_T15.model.CategoryName;

public interface LoyaltyProgramService {
	
	ResponseEntity<String> defineCategories(CategoryDTO categoryDto);
		
	CategoryName[] getCategoryNames();
	
	ResponseEntity<String> definePointsForAppointmentAndConsulation(AppointmentConsultationPointsDTO acpDto);
}
