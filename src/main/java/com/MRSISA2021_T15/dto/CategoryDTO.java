package com.MRSISA2021_T15.dto;

import com.MRSISA2021_T15.model.CategoryName;

public class CategoryDTO {

	private Integer id;
	private CategoryName categoryName;
	private Integer requiredNumberOfPoints;
	private Integer discount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CategoryName getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(CategoryName categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getRequiredNumberOfPoints() {
		return requiredNumberOfPoints;
	}

	public void setRequiredNumberOfPoints(Integer requiredNumberOfPoints) {
		this.requiredNumberOfPoints = requiredNumberOfPoints;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
}
