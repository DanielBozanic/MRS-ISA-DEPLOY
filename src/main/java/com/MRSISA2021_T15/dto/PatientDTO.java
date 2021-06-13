package com.MRSISA2021_T15.dto;

import com.MRSISA2021_T15.model.CategoryName;

public class PatientDTO extends UserDTO {

	private Integer collectedPoints;
	private CategoryName categoryName;
	private int penals;
	
	public int getPenals() {
		return penals;
	}

	public void setPenals(int penals) {
		this.penals = penals;
	}

	public Integer getCollectedPoints() {
		return collectedPoints;
	}

	public void setCollectedPoints(Integer collectedPoints) {
		this.collectedPoints = collectedPoints;
	}

	public CategoryName getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(CategoryName categoryName) {
		this.categoryName = categoryName;
	}
}
