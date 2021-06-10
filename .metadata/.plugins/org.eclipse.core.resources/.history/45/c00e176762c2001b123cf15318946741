package com.MRSISA2021_T15.service;

import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.model.MedicineSupply;
import com.MRSISA2021_T15.model.OfferStatus;
import com.MRSISA2021_T15.model.PurchaseOrder;
import com.MRSISA2021_T15.model.PurchaseOrderMedicine;
import com.MRSISA2021_T15.model.PurchaseOrderSupplier;
import com.MRSISA2021_T15.model.Supplier;
import com.MRSISA2021_T15.repository.MedicineSupplyRepository;
import com.MRSISA2021_T15.repository.PurchaseOrderMedicineRepository;
import com.MRSISA2021_T15.repository.PurchaseOrderRepository;
import com.MRSISA2021_T15.repository.PurchaseOrderSupplierRepository;
import com.MRSISA2021_T15.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MedicineSupplyRepository medicineSupplyRepository;
	
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	private PurchaseOrderMedicineRepository purchaseOrderMedicineRepository;
	
	@Autowired
	private PurchaseOrderSupplierRepository purchaseOrderSupplierRepository;
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public void updateSupplierData(Supplier supplier) {
		Supplier currentUser = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Supplier updatedSupplier = (Supplier) userRepository.findById(currentUser.getId()).get();
		if (!supplier.getName().equals("")) {
			updatedSupplier.setName(supplier.getName());
		}
		if (!supplier.getSurname().equals("")) {
			updatedSupplier.setSurname(supplier.getSurname());
		}
		if (!supplier.getAddress().equals("")) {
			updatedSupplier.setAddress(supplier.getAddress());
		}
		if (!supplier.getCity().equals("")) {
			updatedSupplier.setCity(supplier.getCity());
		}
		if (!supplier.getCountry().equals("")) {
			updatedSupplier.setCountry(supplier.getCountry());
		}
		if (!supplier.getPhoneNumber().equals("")) {
			updatedSupplier.setPhoneNumber(supplier.getPhoneNumber());
		}
		userRepository.save(updatedSupplier);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public String updatePassword(ChangePassword passwords) {
		String message = "";
		Supplier currentUser = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Supplier updatedSupplier = (Supplier) userRepository.findById(currentUser.getId()).get();
		if (!passwordEncoder.matches(passwords.getOldPassword(), updatedSupplier.getPassword())) {
			message = "Wrong old password!";
		} else {
			if (updatedSupplier.getFirstLogin()) {
				updatedSupplier.setFirstLogin(false);
			}
			updatedSupplier.setPassword(passwordEncoder.encode(passwords.getPassword()));
			userRepository.save(updatedSupplier);
		}
		return message;
	}

	@Transactional(readOnly = true)
	@Override
	public Supplier getSupplierData() {
		return (Supplier) userRepository.findById(((Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<MedicineSupply> getMedicineSupply() {
		Supplier supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return medicineSupplyRepository.findAllBySupplierId(supplier.getId());
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<PurchaseOrder> getOrders() {
		return purchaseOrderRepository.findOrdersByDueDateAfterCurrentDate(LocalDate.now());
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public String writeOffer(PurchaseOrderSupplier offer) {
		String message = "";
		Supplier supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Supplier supplierDb = (Supplier) userRepository.findById(supplier.getId()).get();
		if (supplierDb.getFirstLogin()) {
			message =  "You are logging in for the first time, you must change password before you can use this functionality!";
		} else if (offer.getPurchaseOrder().getDueDateOffer().isBefore(LocalDate.now())) {
			message = "Due date for purchase order has passed!";
		} else if (LocalDate.now().isAfter(offer.getDeliveryDate())) {
			message = "Delivery date must be after today's date!";
		} else {
			PurchaseOrderSupplier pos = purchaseOrderSupplierRepository.findBySupplierIdAndPurchaseOrderId(offer.getPurchaseOrder().getId(), supplier.getId());
			if (pos == null) {
				List<PurchaseOrderMedicine> medicine = purchaseOrderMedicineRepository.findAllByPurchaseOrder(offer.getPurchaseOrder());
				List<Integer> pendingPurchaseOrderIds = purchaseOrderSupplierRepository.getPendingPurchaseOrderIdsBySupplierId(supplier.getId());
				boolean offerOk = true;
				for (PurchaseOrderMedicine pom : medicine) {
					Integer sum = purchaseOrderSupplierRepository.getTotalMedicineQuantityFromPurchaseOrders(pom.getMedicine().getId(), pendingPurchaseOrderIds);
					if (sum == null) {
						sum = 0;
					}
					MedicineSupply ms = medicineSupplyRepository.getMedicineSupplyBySupplierPessimisticWrite(pom.getMedicine().getMedicineCode(), supplier.getId());
					if (sum + pom.getQuantity() > ms.getQuantity()) {
						offerOk = false;
						break;
					}
				}
				if (offerOk) {
					offer.setOfferStatus(OfferStatus.PENDING);
					offer.setSupplier(supplierDb);
					offer.setPrice(Math.abs(offer.getPrice()));
					purchaseOrderSupplierRepository.save(offer);
				} else {
					message = "You do not have enough of medicine in stock!";
				}
			} else {
				message = "Supplier has already given an offer for this order!";
			}
		}
		return message;
	}

	@Transactional(readOnly = true)
	@Override
	public List<PurchaseOrderMedicine> getPurchaseOrdersMedicine(PurchaseOrder purchaseOrder) {
		return purchaseOrderMedicineRepository.findAllByPurchaseOrder(purchaseOrder);
	}

	@Transactional(readOnly = true)
	@Override
	public List<PurchaseOrderSupplier> getOffersBySupplier() {
		return purchaseOrderSupplierRepository.findBySupplier((Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	}

	@Transactional(readOnly = true)
	@Override
	public List<PurchaseOrderSupplier> getPendingOffersBySupplier() {
		return purchaseOrderSupplierRepository.getPendingOffers(((Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public String updateOffer(PurchaseOrderSupplier offer) {
		String message = "";
		Supplier supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Supplier supplierDb = (Supplier) userRepository.findById(supplier.getId()).get();
		if (supplierDb.getFirstLogin()) {
			message =  "You are logging in for the first time, you must change password before you can use this functionality!";
		} else {
			PurchaseOrderSupplier offerToUpdate = purchaseOrderSupplierRepository.findByIdAndSupplierIdPessimisticWrite(offer.getId(), supplier.getId());
			if (offerToUpdate != null) {
				if (LocalDate.now().isAfter(offerToUpdate.getDeliveryDate())) {
					message = "Delivery date must be after today's date!";
				} else if (offerToUpdate.getPurchaseOrder().getDueDateOffer().isBefore(LocalDate.now())) {
					message = "Due date for purchase order has passed!";
				} else {
					if (offer.getPrice() != null) {
						offerToUpdate.setPrice(Math.abs(offer.getPrice()));
					}
					if (offer.getDeliveryDate() != null) {
						offerToUpdate.setDeliveryDate(offer.getDeliveryDate());
					}
					purchaseOrderSupplierRepository.save(offerToUpdate);
				}
			} else {
				message = "This offer cannot be updated!";
			}
		}
		return message;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED) 
	@Override
	public ResponseEntity<String> updateMedicineStock(MedicineSupply medicineSupply) {
		String message = "";
		Gson gson = new GsonBuilder().create();
		Supplier supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Supplier supplierDb = (Supplier) userRepository.findById(supplier.getId()).get();
		if (supplierDb.getFirstLogin()) {
			message = "You are logging in for the first time, you must change password before you can use this functionality!";
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			MedicineSupply ms = medicineSupplyRepository.getMedicineSupplyBySupplierPessimisticWrite(medicineSupply.getMedicine().getMedicineCode(), supplier.getId());
			if (ms != null) {
				List<Integer> pendingPurchaseOrderIds = purchaseOrderSupplierRepository.getPendingPurchaseOrderIdsBySupplierId(supplier.getId());
				Integer sum = purchaseOrderSupplierRepository.getTotalMedicineQuantityFromPurchaseOrders(ms.getMedicine().getId(), pendingPurchaseOrderIds);
				if (sum == null) {
					sum = 0;
				}
				if (sum > Math.abs(medicineSupply.getQuantity())) {
					message = "This medicine has more pending offers than entered quantity!";
					return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
				} else {
					ms.setQuantity(Math.abs(medicineSupply.getQuantity()));
					medicineSupplyRepository.save(ms);
					message = "Medicine stock updated.";
				}
			} else {
				medicineSupply.setSupplier(supplier);
				medicineSupply.setQuantity(Math.abs(medicineSupply.getQuantity()));
				medicineSupplyRepository.save(medicineSupply);
				message = "Medicine is added to stock.";
			}
		}
		return new ResponseEntity<String>(gson.toJson(message), HttpStatus.OK);
	}
}