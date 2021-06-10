package com.MRSISA2021_T15.model;

public enum MedicineForm {
	POWDER("Powder"),
	CAPSULE("Capsule"), 
	PILL("Pill"),
	FAT("Fat"),
	PASTE("Paste"),
	GEL("Gel"),
	SIRUP("Sirup"),
	LIQUID("Liquid");
	
	String form;
	
	private MedicineForm(String form) { this.form = form; }
	
	@Override
	public String toString() {
		return this.form;
	}
	
	public static MedicineForm fromString(String text) {
        for (MedicineForm mf : MedicineForm.values()) {
            if (mf.toString().equalsIgnoreCase(text)) {
                return mf;
            }
        }
        return null;
	}
}
