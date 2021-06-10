package com.MRSISA2021_T15.model;

public enum CategoryName {
	REGULAR("Regular"), 
	SILVER("Silver"), 
	GOLD("Gold");
	
	String categoryName;
	
	private CategoryName(String categoryName) { this.categoryName = categoryName; }
	
	@Override
	public String toString() {
		return this.categoryName;
	}
	
	public static CategoryName fromString(String text) {
        for (CategoryName cn : CategoryName.values()) {
            if (cn.toString().equalsIgnoreCase(text)) {
                return cn;
            }
        }
        return null;
	}
}