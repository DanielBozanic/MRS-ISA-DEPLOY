package com.MRSISA2021_T15.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.Promotion;

@Repository
public interface PromotionRepository extends CrudRepository<Promotion, Integer> {
	
	@Query("select p from Promotion p where p.pharmacy.id = ?1 AND p.endingDate > ?2")
	List<Promotion> getPromotionByPharmacyId(Integer pharmacyId, LocalDate currentDate);

}
