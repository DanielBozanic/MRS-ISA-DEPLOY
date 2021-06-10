package com.MRSISA2021_T15.repository;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;


import com.MRSISA2021_T15.model.Employment;
import com.MRSISA2021_T15.model.EmploymentDermatologist;
import com.MRSISA2021_T15.model.EmploymentPharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EmploymentRepository extends JpaRepository<Employment, Integer>{

	@Query("select e from Employment e where pharmacist.id = ?1")
	public EmploymentPharmacist findByPharmacistId(Integer id);
	
	@Query("select e from Employment e where dermatologist.id = ?1")
	public List<EmploymentDermatologist> findByDermatologistId(Integer id);
	

	@Lock(LockModeType.PESSIMISTIC_READ)
	@Query("select e from Employment e where pharmacist.id = ?1")
	public EmploymentPharmacist findByPharmacistIdPessimisticRead(Integer id);
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	@Query("select e from Employment e where dermatologist.id = ?1")
	public List<EmploymentDermatologist> findByDermatologistIdPessimisticRead(Integer id);
	
	@Query("select e from Employment e where EMPLOYMENT_TYPE = 'EMPLOYMENT_PHARMACIST'")
	public List<EmploymentPharmacist> findAllPharmacist();
	

	public List<Employment> findAll();


}
