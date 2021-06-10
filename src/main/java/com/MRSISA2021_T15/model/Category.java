package com.MRSISA2021_T15.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@NonNull
	private CategoryName categoryName;
	
	@Column
	@NonNull
	private Integer requiredNumberOfPoints;
	
	@Column
	@NonNull
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

