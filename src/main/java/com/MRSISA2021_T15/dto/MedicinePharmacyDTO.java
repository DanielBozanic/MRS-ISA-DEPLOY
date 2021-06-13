package com.MRSISA2021_T15.dto;

import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.Pharmacy;

public class MedicinePharmacyDTO {

	private Integer id;
	private Double cost;
	private Integer amount;
	private Pharmacy pharmacy;
	private Medicine medicine;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Double getCost() {
		return cost;
	}
	
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	public Integer getAmount() {
		return amount;
	}
	
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Pharmacy getPharmacy() {
		return pharmacy;
	}
	
	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	
	public Medicine getMedicine() {
		return medicine;
	}
	
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
}
