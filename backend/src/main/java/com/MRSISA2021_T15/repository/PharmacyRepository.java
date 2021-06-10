package com.MRSISA2021_T15.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.MRSISA2021_T15.model.Pharmacy;

@Repository
public interface PharmacyRepository extends CrudRepository<Pharmacy, Integer> {
	
	@Query("select p from Pharmacy p where p.id = ?1")
	public Pharmacy findPharmacyWithId(Integer id);
	
}
