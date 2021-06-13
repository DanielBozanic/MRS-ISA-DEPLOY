package com.MRSISA2021_T15.dto;

import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.Supplier;

public class MedicineSupplyDTO {

	private Integer id;
	private Integer quantity;
	private Medicine medicine;
	private Supplier supplier;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}
