package com.MRSISA2021_T15.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.SubstituteMedicine;

@Repository
public interface SubstituteMedicineRepository extends CrudRepository<SubstituteMedicine, Integer> {

	@Query("select distinct m.substituteMedicine from SubstituteMedicine m where m.medicine.id = ?1")
	List<Medicine> findAllForMedicine(Integer id);
}
