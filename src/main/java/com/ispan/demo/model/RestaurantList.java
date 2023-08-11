package com.ispan.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="restaurant_list")
public class RestaurantList {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "longtitude")
	private Float longtitude;
	
	@Column(name = "latitude")
	private Float latitude;
	
	@Column(name = "area_id")
	private Integer areaId;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "google_score", columnDefinition = "decimal(2, 1)")
	private Float googleScore;
	
	@Column(name = "google_score_count" )
	private Integer googleScoreCount;
	
	@Column(name = "website")
	private String website;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "our_score", columnDefinition = "decimal(2, 1)")
	private Float ourScore;
	
	@Column(name = "our_score_count")
	private Integer ourScoreCount;
	
	@Column(name = "price_level")
	private Integer priceLevel;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "takeout")
	private Boolean takeout;
	
	@Column(name = "dine_in")
	private Boolean dineIn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(Float longtitude) {
		this.longtitude = longtitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Float getGoogleScore() {
		return googleScore;
	}

	public void setGoogleScore(Float googleScore) {
		this.googleScore = googleScore;
	}

	public Integer getGoogleScoreCount() {
		return googleScoreCount;
	}

	public void setGoogleScoreCount(Integer googleScoreCount) {
		this.googleScoreCount = googleScoreCount;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Float getOurScore() {
		return ourScore;
	}

	public void setOurScore(Float ourScore) {
		this.ourScore = ourScore;
	}

	public Integer getOurScoreCount() {
		return ourScoreCount;
	}

	public void setOurScoreCount(Integer ourScoreCount) {
		this.ourScoreCount = ourScoreCount;
	}

	public Integer getPriceLevel() {
		return priceLevel; 
	}

	public void setPriceLevel(Integer priceLevel) {
		this.priceLevel = priceLevel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getTakeout() {
		return takeout;
	}

	public void setTakeout(Boolean takeout) {
		this.takeout = takeout;
	}

	public Boolean getDineIn() {
		return dineIn;
	}

	public void setDineIn(Boolean dineIn) {
		this.dineIn = dineIn;
	}

	public RestaurantList() {
		super();
	}

	@Override
	public String toString() {
		return "RestaurantList [id=" + id + ", name=" + name + ", longtitude=" + longtitude + ", latitude=" + latitude
				+ ", areaId=" + areaId + ", address=" + address + ", googleScore=" + googleScore + ", googleScoreCount="
				+ googleScoreCount + ", website=" + website + ", userId=" + userId + ", status=" + status
				+ ", ourScore=" + ourScore + ", ourScoreCount=" + ourScoreCount + ", priceLevel=" + priceLevel
				+ ", phone=" + phone + ", url=" + url + ", takeout=" + takeout + ", dineIn=" + dineIn + "]";
	}
	
}
