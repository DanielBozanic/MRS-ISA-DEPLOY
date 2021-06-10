package com.MRSISA2021_T15.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.model.MedicineSupply;
import com.MRSISA2021_T15.model.PurchaseOrder;
import com.MRSISA2021_T15.model.PurchaseOrderMedicine;
import com.MRSISA2021_T15.model.PurchaseOrderSupplier;
import com.MRSISA2021_T15.model.Supplier;

public interface SupplierService {
	
	void updateSupplierData(Supplier supplier);
	
	String updatePassword(ChangePassword passwords);
	
	Supplier getSupplierData();
	
	List<MedicineSupply> getMedicineSupply();
	
	List<PurchaseOrder> getOrders();
	
	List<PurchaseOrderMedicine> getPurchaseOrdersMedicine(PurchaseOrder purchaseOrder);
	
	String writeOffer(PurchaseOrderSupplier offer);
	
	List<PurchaseOrderSupplier> getOffersBySupplier();
	
	List<PurchaseOrderSupplier> getPendingOffersBySupplier();
	
	String updateOffer(PurchaseOrderSupplier offer);

	ResponseEntity<String> updateMedicineStock(MedicineSupply medicineSupply);

}
