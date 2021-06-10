package com.MRSISA2021_T15.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "eReceipt")
public class EReceipt {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column
	@NonNull
	private String eReceiptCode;
	
	@ManyToOne
	@NonNull
	private Patient patient;
	
	@Column
	@NonNull
	private LocalDate issueDate;
	
	@Column
	@NonNull
	private Double total;
	
	@Column
	@NonNull
	private Double discount;
	
	@Column(columnDefinition = "TEXT")
	@NonNull
	private String qrCode;

	@OneToMany(mappedBy = "eReceipt", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<EReceiptAndMedicineDetails> eReceiptAndMedicineDetails;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String geteReceiptCode() {
		return eReceiptCode;
	}

	public void seteReceiptCode(String eReceiptCode) {
		this.eReceiptCode = eReceiptCode;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public Set<EReceiptAndMedicineDetails> geteReceiptAndMedicineDetails() {
		return eReceiptAndMedicineDetails;
	}

	public void seteReceiptAndMedicineDetails(Set<EReceiptAndMedicineDetails> eReceiptAndMedicineDetails) {
		this.eReceiptAndMedicineDetails = eReceiptAndMedicineDetails;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
}
