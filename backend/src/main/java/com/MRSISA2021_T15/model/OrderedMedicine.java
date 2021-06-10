package com.MRSISA2021_T15.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ordered_medicines")
public class OrderedMedicine {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private LocalDateTime until;
	
	@Column
	private int amount;
	
	@ManyToOne
	@JoinColumn(name = "medicine_pharmacy_id")
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
