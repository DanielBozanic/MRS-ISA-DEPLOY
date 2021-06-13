package com.MRSISA2021_T15.dto;

import java.time.LocalDateTime;

import com.MRSISA2021_T15.model.MedicinePharmacy;

public class OrderedMedicineDTO {
	
	private Integer id;
	private LocalDateTime until;
	private int amount;
	private MedicinePharmacy medicinePharmacy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getUntil() {
		return until;
	}

	public void setUntil(LocalDateTime until) {
		this.until = until;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public MedicinePharmacy getMedicinePharmacy() {
		return medicinePharmacy;
	}

	public void setMedicinePharmacy(MedicinePharmacy medicinePharmacy) {
		this.medicinePharmacy = medicinePharmacy;
	}

}
