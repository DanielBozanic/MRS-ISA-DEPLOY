package com.MRSISA2021_T15.repository;

import com.MRSISA2021_T15.model.Allergy;
import com.MRSISA2021_T15.model.Medicine;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AllergyRepository extends CrudRepository<Allergy, Integer> {
	
	@Query("select a from Allergy a where patient.id = ?1")
	List<Allergy> findAllPatients(Integer id);
	

	@Lock(LockModeType.PESSIMISTIC_READ)
	@Query("select a from Allergy a where patient.id = ?1")
	List<Allergy> findAllPatientsPessimisticRead(Integer id);
  
	//@Query("select m from Allergy a join medicine m where patient.id = ?1")
	//List<Medicine> findAllPatientsMedicines(Integer id);
}
