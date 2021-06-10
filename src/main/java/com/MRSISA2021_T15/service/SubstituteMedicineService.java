package com.MRSISA2021_T15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.repository.SubstituteMedicineRepository;

@Service
public class SubstituteMedicineService {

	@Autowired
	SubstituteMedicineRepository repo;
	
	public List<Medicine> getForMedicine(Integer id){
		return repo.findAllForMedicine(id);
	}
}
