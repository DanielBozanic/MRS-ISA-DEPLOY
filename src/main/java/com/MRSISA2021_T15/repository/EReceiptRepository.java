package com.MRSISA2021_T15.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.EReceipt;

@Repository
public interface EReceiptRepository extends CrudRepository<EReceipt, Integer> {
	
	@Query("select e from EReceipt e where e.patient.id = ?1 AND e.qrCode = ?2")
	EReceipt findByPatientIdAndQrCode(Integer patientId, String qrCode);

}
