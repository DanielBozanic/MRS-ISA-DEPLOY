package com.MRSISA2021_T15.model;

import java.util.List;

public class EReceiptSearch {
	
	private Pharmacy pharmacy;
	
	private double total;
	
	private List<EReceiptMedicineDetails> eReceiptMedicineDetails;
	
	private String qrCode;

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<EReceiptMedicineDetails> geteReceiptMedicineDetails() {
		return eReceiptMedicineDetails;
	}

	public void seteReceiptMedicineDetails(List<EReceiptMedicineDetails> eReceiptMedicineDetails) {
		this.eReceiptMedicineDetails = eReceiptMedicineDetails;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
}
