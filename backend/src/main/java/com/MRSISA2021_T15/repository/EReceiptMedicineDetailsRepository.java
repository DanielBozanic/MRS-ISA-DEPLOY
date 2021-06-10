package com.MRSISA2021_T15.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.EReceiptMedicineDetails;

@Repository
public interface EReceiptMedicineDetailsRepository extends CrudRepository<EReceiptMedicineDetails, Integer> {

}
