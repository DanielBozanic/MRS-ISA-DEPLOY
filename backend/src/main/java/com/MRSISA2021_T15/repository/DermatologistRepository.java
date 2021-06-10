package com.MRSISA2021_T15.repository;

import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DermatologistRepository extends CrudRepository<Dermatologist, Integer> {
	User findByEmail(String email);
	
	User findByUsername(String username);
	
	@Query("select distinct d from User d where USER_TYPE = 'DERMATOLOGIST'")
	List<Dermatologist>getAllDermatologist();
}
