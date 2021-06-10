package com.MRSISA2021_T15.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.PurchaseOrderSupplier;
import com.MRSISA2021_T15.model.Supplier;

@Repository
public interface PurchaseOrderSupplierRepository extends CrudRepository<PurchaseOrderSupplier, Integer> {

	@Query("select pos from PurchaseOrderSupplier pos where pos.purchaseOrder.id = ?1 and pos.supplier.id = ?2")
	PurchaseOrderSupplier findBySupplierIdAndPurchaseOrderId(Integer purchaseOrderId, Integer supplierId);
	
	List<PurchaseOrderSupplier> findBySupplier(Supplier supplier);
	
	@Query("select pos from PurchaseOrderSupplier pos where pos.offerStatus = 2 AND pos.supplier.id = ?1 AND pos.purchaseOrder.dueDateOffer > ?2")
	List<PurchaseOrderSupplier> getPendingOffers(Integer supplierId, LocalDate currentDate);
	
	@Query("select pos.purchaseOrder.id from PurchaseOrderSupplier pos where pos.offerStatus = 2 AND pos.supplier.id = ?1")
	List<Integer> getPendingPurchaseOrderIdsBySupplierId(Integer supplierId);
	
	@Query("select sum(pom.quantity) from PurchaseOrderMedicine pom where pom.medicine.id = :medicineId AND pom.purchaseOrder.id in (:purchaseOrderIds)")
	Integer getTotalMedicineQuantityFromPurchaseOrders(Integer medicineId, List<Integer> purchaseOrderIds);
	
	@Query("select pos from PurchaseOrderSupplier pos where pos.id = ?1 AND pos.supplier.id = ?2")
	PurchaseOrderSupplier findByIdAndSupplierId(Integer id, Integer supplierId);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select pos from PurchaseOrderSupplier pos where pos.id = ?1 AND pos.supplier.id = ?2 AND pos.offerStatus = 2")
	PurchaseOrderSupplier findByIdAndSupplierIdPessimisticWrite(Integer id, Integer supplierId);
}