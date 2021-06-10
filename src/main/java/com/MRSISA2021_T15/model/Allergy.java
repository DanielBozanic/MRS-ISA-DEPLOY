package com.MRSISA2021_T15.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "allergy")
public class Allergy {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	private Integer id;
	@ManyToOne
	@JoinColumn(name = "medicine_id")
	private Medicine medicine;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	public Patient getPatient() {
		return patient;
	}
	

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	
}
