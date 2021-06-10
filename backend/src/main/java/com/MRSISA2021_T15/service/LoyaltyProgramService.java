package com.MRSISA2021_T15.service;

import org.springframework.http.ResponseEntity;

import com.MRSISA2021_T15.model.AppointmentConsultationPoints;
import com.MRSISA2021_T15.model.Category;
import com.MRSISA2021_T15.model.CategoryName;

public interface LoyaltyProgramService {
	
	ResponseEntity<String> defineCategories(Category category);
		
	CategoryName[] getCategoryNames();
	
	ResponseEntity<String> definePointsForAppointmentAndConsulation(AppointmentConsultationPoints appointmentConsultationPoints);
}
