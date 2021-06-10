package com.MRSISA2021_T15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.repository.PharmacySearchRepository;
import com.MRSISA2021_T15.model.Pharmacy;

@Service
public class PharmacySearchService {
	@Autowired
	PharmacySearchRepository repository;
	
	public List<Pharmacy>findAll(){
		return repository.findAllPhamracies();
	}
}
