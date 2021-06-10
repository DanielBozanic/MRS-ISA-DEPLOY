package com.MRSISA2021_T15.repository;

import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.PharmacyAdmin;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByEmail(String email);
	
	User findByUsername(String username);
	
	List<User> findAll();
	
	@Query("select distinct p from User p where USER_TYPE = 'PATIENT'")
	List<Patient>findAllPatients();
	
	@Query("select distinct d from User d where USER_TYPE = 'DERMATOLOGIST'")
	List<Dermatologist>findAllDermatologist();
	
	@Query("select distinct p from User p where USER_TYPE = 'PHARMACIST'")
	List<Pharmacist>findAllPharmacist();
	
	@Query("select distinct p from User p where USER_TYPE = 'PHARMACY_ADMIN'")
	List<PharmacyAdmin>findAllPharmacyAdmins();
	
	@Query("select distinct p from User p where USER_TYPE = 'SYSTEM_ADMIN'")
	List<SystemAdmin>findAllSystemAdmins();
	
	@Query("select d from User d where d.id = ?1")
	public Dermatologist findDermatologistWithId(Integer id);

	@Query("select p from User p where p.id = ?1")
	public Pharmacist findPharmacistWithId(Integer id);
}