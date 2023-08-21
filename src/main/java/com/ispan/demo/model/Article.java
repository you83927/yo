package com.ispan.demo.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="article")
public class Article {

	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "context")
	private String context;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "ispublic")
	private Integer isPublic;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "created_date", columnDefinition = "DATE")
	private LocalDateTime createdDate;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Article() {
		super();
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", userId=" + userId + ", title=" + title + ", context=" + context + ", type="
				+ type + ", isPublic=" + isPublic + ", createdDate=" + createdDate + "]";
	}

}
