package com.ispan.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="food_type")
public class FoodType {

	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name="food_type")
	private String foodType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public FoodType() {
		super();
	}

	@Override
	public String toString() {
		return "FoodType [id=" + id + ", foodType=" + foodType + "]";
	}
	
	
}
