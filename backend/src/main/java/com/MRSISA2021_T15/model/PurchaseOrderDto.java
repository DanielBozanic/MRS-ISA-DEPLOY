package com.MRSISA2021_T15.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class PurchaseOrderDto {

    private ArrayList<PurchaseOrderMedicine> purchaseOrderMedicine;
    private Pharmacy pharmacy;
    private String purchaseOrderName;
    private LocalDate purchaseOrderDate;

    public PurchaseOrderDto() {
    }

    public ArrayList<PurchaseOrderMedicine> getPurchaseOrderMedicine() {
        return purchaseOrderMedicine;
    }

    public void setPurchaseOrderMedicine(ArrayList<PurchaseOrderMedicine> purchaseOrderMedicine) {
        this.purchaseOrderMedicine = purchaseOrderMedicine;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public String getPurchaseOrderName() {
        return purchaseOrderName;
    }

    public void setPurchaseOrderName(String purchaseOrderName) {
        this.purchaseOrderName = purchaseOrderName;
    }

    public LocalDate getPurchaseOrderDate() {
        return purchaseOrderDate;
    }

    public void setPurchaseOrderDate(LocalDate purchaseOrderDate) {
        this.purchaseOrderDate = purchaseOrderDate;
    }
}
