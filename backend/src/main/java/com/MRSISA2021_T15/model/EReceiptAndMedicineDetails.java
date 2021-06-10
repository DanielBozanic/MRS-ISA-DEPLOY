package com.MRSISA2021_T15.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "eReceipt_and_medicine_details")
public class EReceiptAndMedicineDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@NonNull
	private EReceipt eReceipt;
	
	@ManyToOne
	@NonNull
	private EReceiptMedicineDetails eReceiptMedicineDetails;
	
	@ManyToOne
	@NonNull
	private Pharmacy pharmacy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EReceipt geteReceipt() {
		return eReceipt;
	}

	public void seteReceipt(EReceipt eReceipt) {
		this.eReceipt = eReceipt;
	}

	public EReceiptMedicineDetails geteReceiptMedicineDetails() {
		return eReceiptMedicineDetails;
	}

	public void seteReceiptMedicineDetails(EReceiptMedicineDetails eReceiptMedicineDetails) {
		this.eReceiptMedicineDetails = eReceiptMedicineDetails;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
}
