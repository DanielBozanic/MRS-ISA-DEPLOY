package com.MRSISA2021_T15.model;

import javax.persistence.*;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "substitute_medicine")
public class SubstituteMedicine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "medicine_id")
	@NonNull
	private Medicine medicine;
	
	@ManyToOne
	@JoinColumn(name = "substitute_medicine_id")
	@NonNull
	private Medicine substituteMedicine;

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Medicine getSubstituteMedicine() {
		return substituteMedicine;
	}

	public void setSubstituteMedicine(Medicine substituteMedicine) {
		this.substituteMedicine = substituteMedicine;
	}
}
