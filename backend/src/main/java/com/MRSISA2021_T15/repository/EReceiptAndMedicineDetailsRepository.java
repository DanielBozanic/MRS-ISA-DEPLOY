package com.MRSISA2021_T15.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.EReceiptAndMedicineDetails;
import com.MRSISA2021_T15.model.EReceiptMedicineDetails;

@Repository
public interface EReceiptAndMedicineDetailsRepository extends CrudRepository<EReceiptAndMedicineDetails, Integer> {
	@Query("select distinct m from EReceiptAndMedicineDetails er join er.eReceiptMedicineDetails m where er.eReceipt.patient.id = ?1")
	public List<EReceiptMedicineDetails> findAllMedicinesByERecept(Integer id);
	

}
