package com.MRSISA2021_T15.dto;

import java.time.LocalDate;

import com.MRSISA2021_T15.model.OfferStatus;
import com.MRSISA2021_T15.model.PurchaseOrder;
import com.MRSISA2021_T15.model.Supplier;

public class PurchaseOrderSupplierDTO {

	private Integer id;
	private Double price;
	private LocalDate deliveryDate;
	private OfferStatus offerStatus;
	private PurchaseOrder purchaseOrder;
	private Supplier supplier;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
		
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	public OfferStatus getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(OfferStatus offerStatus) {
		this.offerStatus = offerStatus;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}
