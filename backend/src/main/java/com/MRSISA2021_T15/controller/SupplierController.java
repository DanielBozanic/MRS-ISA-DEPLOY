package com.MRSISA2021_T15.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.MedicineSupply;
import com.MRSISA2021_T15.model.PurchaseOrder;
import com.MRSISA2021_T15.model.PurchaseOrderMedicine;
import com.MRSISA2021_T15.model.PurchaseOrderSupplier;
import com.MRSISA2021_T15.model.Supplier;
import com.MRSISA2021_T15.repository.MedicineRepository;
import com.MRSISA2021_T15.repository.PurchaseOrderRepository;
import com.MRSISA2021_T15.service.SupplierService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	private MedicineRepository medicineRepository;
	
	@PutMapping(value = "/updateSupplierData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public ResponseEntity<String> updateSupplierData(@RequestBody Supplier supplier) {
		supplierService.updateSupplierData(supplier);
		Gson gson = new GsonBuilder().create();
		return new ResponseEntity<String>(gson.toJson("Update successfull!"), HttpStatus.OK);
	}
	
	@PutMapping(value = "/updatePassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public ResponseEntity<String> updatePassword(@RequestBody ChangePassword passwords) {
		String message = supplierService.updatePassword(passwords);
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson(""), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/getSupplierData", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public Supplier getSupplierData() {
		return supplierService.getSupplierData();
	}
	
	@GetMapping(value = "/getMedicineSupply", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public List<MedicineSupply> getMedicineSupply() {
		return supplierService.getMedicineSupply();
	}
	
	@PostMapping(value = "/writeOffer", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public ResponseEntity<String> writeOffer(@RequestBody PurchaseOrderSupplier offer) {
		String message = supplierService.writeOffer(offer);
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson("Offer sent successfully."), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/getOrders", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public List<PurchaseOrder> getOrders() {
		return supplierService.getOrders();
	}
	
	@GetMapping(value = "/getPurchaseOrdersMedicine/{purchaseOrderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public List<PurchaseOrderMedicine> getPurchaseOrdersMedicine(@PathVariable Integer purchaseOrderId) {
		return supplierService.getPurchaseOrdersMedicine(purchaseOrderRepository.findById(purchaseOrderId).get());
	}
	
	@GetMapping(value = "/getOffersBySupplier", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public List<PurchaseOrderSupplier> getOffersBySupplier() {
		return supplierService.getOffersBySupplier();
	}
	
	@GetMapping(value = "/getPendingOffersBySupplier", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public List<PurchaseOrderSupplier> getPendingOffersBySupplier() {
		return supplierService.getPendingOffersBySupplier();
	}
	
	@PutMapping(value = "/updateOffer", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public ResponseEntity<String> updateOffer(@RequestBody PurchaseOrderSupplier offer) {
		String message = supplierService.updateOffer(offer);
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson("Offer updated successfully."), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/getAllMedicine", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public List<Medicine> getAllMedicine() {
		return (List<Medicine>) medicineRepository.findAll();
	}
	
	@PostMapping(value = "/updateMedicineStock", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public ResponseEntity<String> updateMedicineStock(@RequestBody MedicineSupply medicineSupply) {
		return supplierService.updateMedicineStock(medicineSupply);
	}
}
