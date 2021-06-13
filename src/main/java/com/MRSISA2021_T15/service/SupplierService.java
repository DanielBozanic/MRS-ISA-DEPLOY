package com.MRSISA2021_T15.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.dto.MedicineSupplyDTO;
import com.MRSISA2021_T15.dto.PurchaseOrderSupplierDTO;
import com.MRSISA2021_T15.dto.SupplierDTO;
import com.MRSISA2021_T15.model.MedicineSupply;
import com.MRSISA2021_T15.model.PurchaseOrder;
import com.MRSISA2021_T15.model.PurchaseOrderMedicine;
import com.MRSISA2021_T15.model.PurchaseOrderSupplier;
import com.MRSISA2021_T15.model.Supplier;

public interface SupplierService {
	
	void updateSupplierData(SupplierDTO supplierDto);
	
	String updatePassword(ChangePassword passwords);
	
	Supplier getSupplierData();
	
	List<MedicineSupply> getMedicineSupply();
	
	List<PurchaseOrder> getOrders();
	
	List<PurchaseOrderMedicine> getPurchaseOrdersMedicine(PurchaseOrder purchaseOrder);
	
	String writeOffer(PurchaseOrderSupplierDTO offerDto);
	
	List<PurchaseOrderSupplier> getOffersBySupplier();
	
	List<PurchaseOrderSupplier> getPendingOffersBySupplier();
	
	String updateOffer(PurchaseOrderSupplierDTO offerDto);

	ResponseEntity<String> updateMedicineStock(MedicineSupplyDTO medicineSupplyDto);

}
