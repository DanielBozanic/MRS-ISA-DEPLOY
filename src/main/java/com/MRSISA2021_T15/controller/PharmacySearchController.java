package com.MRSISA2021_T15.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.service.PharmacySearchService;
import com.MRSISA2021_T15.model.Pharmacy;

@RestController
@RequestMapping("/pharmacySearch")
public class PharmacySearchController {

	@Autowired
	PharmacySearchService service;
	
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pharmacy>getAll(){
		return service.findAll();
	}
	
}
