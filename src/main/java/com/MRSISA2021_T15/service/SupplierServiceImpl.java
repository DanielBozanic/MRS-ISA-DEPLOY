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
import com.MRSISA2021_T15.dto.MedicineSupplyDTO;
import com.MRSISA2021_T15.dto.PurchaseOrderSupplierDTO;
import com.MRSISA2021_T15.dto.SupplierDTO;
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
	
	private String login = "You are logging in for the first time, you must change password before you can use this functionality!";
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public void updateSupplierData(SupplierDTO supplierDto) {
		var currentUser = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var updatedSupplier = (Supplier) userRepository.findById(currentUser.getId()).orElse(null);
		if (updatedSupplier != null) {
			if (!supplierDto.getName().equals("")) {
				updatedSupplier.setName(supplierDto.getName());
			}
			if (!supplierDto.getSurname().equals("")) {
				updatedSupplier.setSurname(supplierDto.getSurname());
			}
			if (!supplierDto.getAddress().equals("")) {
				updatedSupplier.setAddress(supplierDto.getAddress());
			}
			if (!supplierDto.getCity().equals("")) {
				updatedSupplier.setCity(supplierDto.getCity());
			}
			if (!supplierDto.getCountry().equals("")) {
				updatedSupplier.setCountry(supplierDto.getCountry());
			}
			if (!supplierDto.getPhoneNumber().equals("")) {
				updatedSupplier.setPhoneNumber(supplierDto.getPhoneNumber());
			}
			userRepository.save(updatedSupplier);
		}
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public String updatePassword(ChangePassword passwords) {
		var message = "";
		var currentUser = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var updatedSupplier = (Supplier) userRepository.findById(currentUser.getId()).orElse(null);
		if (updatedSupplier != null) {
			if (!passwordEncoder.matches(passwords.getOldPassword(), updatedSupplier.getPassword())) {
				message = "Wrong old password!";
			} else {
				if (updatedSupplier.getFirstLogin()) {
					updatedSupplier.setFirstLogin(false);
				}
				updatedSupplier.setPassword(passwordEncoder.encode(passwords.getPassword()));
				userRepository.save(updatedSupplier);
			}
		}
		return message;
	}

	@Transactional(readOnly = true)
	@Override
	public Supplier getSupplierData() {
		return (Supplier) userRepository.findById(((Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public List<MedicineSupply> getMedicineSupply() {
		var supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return medicineSupplyRepository.findAllBySupplierId(supplier.getId());
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<PurchaseOrder> getOrders() {
		return purchaseOrderRepository.findOrdersByDueDateAfterCurrentDate(LocalDate.now());
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public String writeOffer(PurchaseOrderSupplierDTO offerDto) {
		var message = "";
		var supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var supplierDb = (Supplier) userRepository.findById(supplier.getId()).orElse(null);
		if (supplierDb !=  null) {
			if (supplierDb.getFirstLogin()) {
				message =  login;
			} else if (offerDto.getPurchaseOrder().getDueDateOffer().isBefore(LocalDate.now())) {
				message = "Due date for purchase order has passed!";
			} else if (LocalDate.now().isAfter(offerDto.getDeliveryDate())) {
				message = "Delivery date must be after today's date!";
			} else {
				var pos = purchaseOrderSupplierRepository.findBySupplierIdAndPurchaseOrderId(offerDto.getPurchaseOrder().getId(), supplier.getId());
				if (pos == null) {
					List<PurchaseOrderMedicine> medicine = purchaseOrderMedicineRepository.findAllByPurchaseOrder(offerDto.getPurchaseOrder());
					List<Integer> pendingPurchaseOrderIds = purchaseOrderSupplierRepository.getPendingPurchaseOrderIdsBySupplierId(supplier.getId());
					boolean offerOk = true;
					for (PurchaseOrderMedicine pom : medicine) {
						var sum = purchaseOrderSupplierRepository.getTotalMedicineQuantityFromPurchaseOrders(pom.getMedicine().getId(), pendingPurchaseOrderIds);
						if (sum == null) {
							sum = 0;
						}
						var ms = medicineSupplyRepository.getMedicineSupplyBySupplierPessimisticWrite(pom.getMedicine().getMedicineCode(), supplier.getId());
						if (sum + pom.getQuantity() > ms.getQuantity()) {
							offerOk = false;
							break;
						}
					}
					if (offerOk) {
						var offer = new PurchaseOrderSupplier();
						offer.setPurchaseOrder(offerDto.getPurchaseOrder());
						offer.setDeliveryDate(offerDto.getDeliveryDate());
						offer.setPrice(offerDto.getPrice());
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
		return purchaseOrderSupplierRepository.getPendingOffers(((Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(), LocalDate.now());
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public String updateOffer(PurchaseOrderSupplierDTO offerDto) {
		var message = "";
		var supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var supplierDb = (Supplier) userRepository.findById(supplier.getId()).orElse(null);
		if (supplierDb != null) {
			if (supplierDb.getFirstLogin()) {
				message =  login;
			} else {
				var offerToUpdate = purchaseOrderSupplierRepository.findByIdAndSupplierIdPessimisticWrite(offerDto.getId(), supplier.getId());
				if (offerToUpdate != null) {
					if (LocalDate.now().isAfter(offerDto.getDeliveryDate())) {
						message = "Delivery date must be after today's date!";
					} else if (offerToUpdate.getPurchaseOrder().getDueDateOffer().isBefore(LocalDate.now())) {
						message = "Due date for purchase order has passed!";
					} else {
						if (offerDto.getPrice() != null) {
							offerToUpdate.setPrice(Math.abs(offerDto.getPrice()));
						}
						if (offerDto.getDeliveryDate() != null) {
							offerToUpdate.setDeliveryDate(offerDto.getDeliveryDate());
						}
						purchaseOrderSupplierRepository.save(offerToUpdate);
					}
				} else {
					message = "This offer cannot be updated!";
				}
			}
		}
		return message;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED) 
	@Override
	public ResponseEntity<String> updateMedicineStock(MedicineSupplyDTO medicineSupplyDto) {
		var message = "";
		var gson = new GsonBuilder().create();
		var supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var supplierDb = (Supplier) userRepository.findById(supplier.getId()).orElse(null);
		if (supplierDb != null) {
			if (supplierDb.getFirstLogin()) {
				message = login;
				return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				var ms = medicineSupplyRepository.getMedicineSupplyBySupplierPessimisticWrite(medicineSupplyDto.getMedicine().getMedicineCode(), supplier.getId());
				if (ms != null) {
					List<Integer> pendingPurchaseOrderIds = purchaseOrderSupplierRepository.getPendingPurchaseOrderIdsBySupplierId(supplier.getId());
					var sum = purchaseOrderSupplierRepository.getTotalMedicineQuantityFromPurchaseOrders(ms.getMedicine().getId(), pendingPurchaseOrderIds);
					if (sum == null) {
						sum = 0;
					}
					if (sum > Math.abs(medicineSupplyDto.getQuantity())) {
						message = "This medicine has more pending offers than entered quantity!";
						return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
					} else {
						ms.setQuantity(Math.abs(medicineSupplyDto.getQuantity()));
						medicineSupplyRepository.save(ms);
						message = "Medicine stock updated.";
					}
				} else {
					var medicineSupply = new MedicineSupply();
					medicineSupply.setMedicine(medicineSupplyDto.getMedicine());
					medicineSupply.setSupplier(supplier);
					medicineSupply.setQuantity(Math.abs(medicineSupplyDto.getQuantity()));
					medicineSupplyRepository.save(medicineSupply);
					message = "Medicine is added to stock.";
				}
			}
		}
		return new ResponseEntity<>(gson.toJson(message), HttpStatus.OK);
	}
}