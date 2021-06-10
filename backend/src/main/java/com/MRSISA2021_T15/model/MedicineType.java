package com.MRSISA2021_T15.model;

public enum MedicineType {
	ANTIBIOTIC("Antibiotic"),
	ANALGESIC("Analgesic"),
	ANTIHISTAMINE("Antihistamine"),
	BENZODIAZEPINE("Benzodiazepine"),
	ANTISEPTIC("Antiseptic"),
	ANTIMIGRAINE("Antimigraine");
	
	String type;
	
	private MedicineType(String type) { this.type = type; }
	
	@Override
	public String toString() {
		return this.type;
	}
	
	public static MedicineType fromString(String text) {
        for (MedicineType mt : MedicineType.values()) {
            if (mt.toString().equalsIgnoreCase(text)) {
                return mt;
            }
        }
        return null;
	}
}
