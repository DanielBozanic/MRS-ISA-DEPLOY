package com.MRSISA2021_T15.repository;

import com.MRSISA2021_T15.model.Medicine;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends CrudRepository<Medicine, Integer> {
	
	Medicine findByMedicineCode(String medicineCode);
	
	@Query("select m.points from Medicine m where m.medicineCode = ?1")
	Integer getPointsByMedicineCode(String medicineCode);

	@Query("select m from Medicine m where m.id = ?1")
	public Medicine findMedicineWithId(Integer id);
	
	@Query("select m from Medicine m")
	public List<Medicine> findAllMedicines();
	
}
