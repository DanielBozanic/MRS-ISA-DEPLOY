package com.MRSISA2021_T15.model;

public enum OfferStatus {
	ACCEPTED("Accepted"),
	REJECTED("Rejected"), 
	PENDING("Pending");
	
	String offerStatus;
	
	private OfferStatus(String offerStatus) { this.offerStatus = offerStatus; }
	
	@Override
	public String toString() {
		return this.offerStatus;
	}
	
	public static OfferStatus fromString(String text) {
        for (OfferStatus os : OfferStatus.values()) {
            if (os.toString().equalsIgnoreCase(text)) {
                return os;
            }
        }
        return null;
	}
}
