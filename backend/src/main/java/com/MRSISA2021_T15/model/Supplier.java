package com.MRSISA2021_T15.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue(value = "SUPPLIER")
public class Supplier extends User{
	
	private static final long serialVersionUID = 1L;
	
	@Transient
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<MedicineSupply> medicineSupply = new HashSet<MedicineSupply>();

	public Supplier() {
		super();

	}

	public Set<MedicineSupply> getMedicineSupply() {
		return medicineSupply;
	}

	public void setMedicineSupply(Set<MedicineSupply> medicineSupply) {
		this.medicineSupply = medicineSupply;
	}
}
