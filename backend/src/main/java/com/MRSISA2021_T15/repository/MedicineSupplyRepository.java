package com.MRSISA2021_T15.repository;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.MedicineSupply;

@Repository
public interface MedicineSupplyRepository extends CrudRepository<MedicineSupply, Integer> {
	
	List<MedicineSupply> findAllBySupplierId(Integer supplierId);
	
	@Query("select ms from PurchaseOrderMedicine pom left join MedicineSupply ms on pom.medicine.id = ms.medicine.id "
			+ "where pom.purchaseOrder.id = ?1 AND ms.supplier.id = ?2 AND ms.quantity < pom.quantity")
	List<MedicineSupply> hasNoMedicineInStock(Integer orderId, Integer supplierId);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select ms from PurchaseOrderMedicine pom left join MedicineSupply ms on pom.medicine.id = ms.medicine.id "
			+ "where pom.purchaseOrder.id = ?1 AND ms.supplier.id = ?2 AND ms.quantity < pom.quantity")
	List<MedicineSupply> hasNoMedicineInStockPessimisticWrite(Integer orderId, Integer supplierId);
	
	@Query("select ms from MedicineSupply ms where ms.medicine.medicineCode = ?1 AND ms.supplier.id = ?2")
	MedicineSupply getMedicineSupplyBySupplier(String medicineCode, Integer supplierId);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select ms from MedicineSupply ms where ms.medicine.medicineCode = ?1 AND ms.supplier.id = ?2")
	MedicineSupply getMedicineSupplyBySupplierPessimisticWrite(String medicineCode, Integer supplierId);

}