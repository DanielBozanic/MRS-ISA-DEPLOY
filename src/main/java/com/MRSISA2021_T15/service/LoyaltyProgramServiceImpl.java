package com.MRSISA2021_T15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.MRSISA2021_T15.dto.AppointmentConsultationPointsDTO;
import com.MRSISA2021_T15.dto.CategoryDTO;
import com.MRSISA2021_T15.model.AppointmentConsultationPoints;
import com.MRSISA2021_T15.model.Category;
import com.MRSISA2021_T15.model.CategoryName;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.repository.AppointmentConsultationPointsRepository;
import com.MRSISA2021_T15.repository.CategoryRepository;
import com.MRSISA2021_T15.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class LoyaltyProgramServiceImpl implements LoyaltyProgramService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private AppointmentConsultationPointsRepository appointmentConsultationPointsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public ResponseEntity<String> defineCategories(CategoryDTO categoryDto) {
		String message = "";
		Gson gson = new GsonBuilder().create();
		SystemAdmin systemAdmin = (SystemAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SystemAdmin systemAdminDb = (SystemAdmin) userRepository.findById(systemAdmin.getId()).orElse(null);
		if (systemAdminDb != null) {
			if (systemAdminDb.getFirstLogin()) {
				message =  "You are logging in for the first time, you must change password before you can use this functionality!";
				return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				Category ca = categoryRepository.findByCategoryName(categoryDto.getCategoryName());
				if (ca != null) {
					ca.setDiscount(Math.abs(categoryDto.getDiscount()));
					ca.setRequiredNumberOfPoints(Math.abs(categoryDto.getRequiredNumberOfPoints()));
					categoryRepository.save(ca);
					message = "Category information updated.";
				} else {
					Category category = new Category();
					category.setCategoryName(categoryDto.getCategoryName());
					category.setDiscount(Math.abs(categoryDto.getDiscount()));
					category.setRequiredNumberOfPoints(Math.abs(categoryDto.getRequiredNumberOfPoints()));
					categoryRepository.save(category);
					message = "Category successfully defined.";
				}
			}
		}
		return new ResponseEntity<>(gson.toJson(message), HttpStatus.OK);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public ResponseEntity<String> definePointsForAppointmentAndConsulation(AppointmentConsultationPointsDTO acpDto) {
		String message = "";
		Gson gson = new GsonBuilder().create();
		SystemAdmin systemAdmin = (SystemAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SystemAdmin systemAdminDb = (SystemAdmin) userRepository.findById(systemAdmin.getId()).orElse(null);
		if (systemAdminDb != null) {
			if (systemAdminDb.getFirstLogin()) {
				message =  "You are logging in for the first time, you must change password before you can use this functionality!";
				return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				AppointmentConsultationPoints acp = appointmentConsultationPointsRepository.findByType(acpDto.getType());
				if (acp != null) {
					acp.setPoints(Math.abs(acpDto.getPoints()));
					appointmentConsultationPointsRepository.save(acp);
					message = "Points for " + acpDto.getType() + " successfully updated.";
				} else {
					AppointmentConsultationPoints acpNew = new AppointmentConsultationPoints();
					acpNew.setType(acpDto.getType());
					acpNew.setPoints(Math.abs(acpDto.getPoints()));
					appointmentConsultationPointsRepository.save(acpNew);
					message = "Points for " + acpNew.getType() + " successfully defined.";
				}
			}
		}
		return new ResponseEntity<>(gson.toJson(message), HttpStatus.OK);
	}

	@Override
	public CategoryName[] getCategoryNames() {
		CategoryName[] cn = {CategoryName.SILVER, CategoryName.GOLD};
		return cn;
	}
}
