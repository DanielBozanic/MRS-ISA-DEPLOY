package com.MRSISA2021_T15.repository;


import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacistRepository extends CrudRepository<Pharmacist, Integer> {

	User findByEmail(String email);
	
	User findByUsername(String username);
	
	@Query("select distinct p from User p where USER_TYPE = 'PHARMACIST'")
	List<Pharmacist>getAllPharmacist();
}