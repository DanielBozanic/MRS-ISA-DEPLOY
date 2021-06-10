package com.MRSISA2021_T15.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.PurchaseOrder;
import com.MRSISA2021_T15.model.PurchaseOrderMedicine;

@Repository
public interface PurchaseOrderMedicineRepository extends CrudRepository<PurchaseOrderMedicine, Integer> {
	
	List<PurchaseOrderMedicine> findAllByPurchaseOrder(PurchaseOrder purchaseOrder);
}
