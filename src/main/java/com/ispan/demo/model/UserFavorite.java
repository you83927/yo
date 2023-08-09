package com.ispan.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
//@Data
@Table(name = "user_fav")
public class UserFavorite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
//	@ManyToOne
	@JoinColumn(name = "user_id")
	@Column(name = "user_id")
	private Integer  userId;
//	private User  user;
	
//	@ManyToOne
	@JoinColumn(name = "article_id")
	@Column(name = "article_id")
	private Integer articleId;
//	private Article article;
	
//	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	@Column(name = "restaurant_id")
	private Integer restaurantId;
//	private Restaurant restaurant;
	
//	@ManyToOne
	@JoinColumn(name = "food_id")
	@Column(name = "food_id")
	private Integer foodId;
//	private Food food;

	public UserFavorite() {
		super();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	
	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	
}
