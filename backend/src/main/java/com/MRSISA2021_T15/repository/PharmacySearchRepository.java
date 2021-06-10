package com.MRSISA2021_T15.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.MRSISA2021_T15.model.Pharmacy;

public interface PharmacySearchRepository extends JpaRepository<Pharmacy, Integer>{
	@Query("select p from Pharmacy p")
	public List<Pharmacy> findAllPhamracies();
}
