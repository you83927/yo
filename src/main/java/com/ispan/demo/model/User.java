package com.ispan.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "[user]")
@Data
public class User {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "username",columnDefinition = "nvarchar(15)",unique = true)
	private String userName;
	
	@Column(name = "password",columnDefinition = "nvarchar(15)")
	private String passWord;
	
	@Column(name = "[identity]")
	private Integer identity;
	
	@Column(name = "nickname",columnDefinition = "nvarchar(25)")
	private String nickName;
	
	@Lob
	@Column(name = "photo",columnDefinition = "varbinary(max)")
	private byte[] photo;
	
	@Column(name = "gender",columnDefinition = "smallint")
	private Integer gender;
	
	@Column(name = "email",columnDefinition = "nvarchar(225)")
	private String email;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birthday",columnDefinition = "date")
	private LocalDate birthday;
	
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "violate_count")
	private Integer violateCount;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "modified_date",columnDefinition = "datetime")
	private LocalDateTime modifiedDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "created_date",columnDefinition = "datetime")
	private LocalDateTime createdDate;
	
	@Column(name = "modified_by",columnDefinition = "sysname")
	private String modifiedBy;
	
	@PrePersist
	public void onCreate() {
		if(createdDate==null) {
			createdDate =  LocalDateTime.now();
		}
	}
	
	@PreUpdate
	public void onUpdate() {
			modifiedDate=LocalDateTime.now();
		}
	
	

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getIdentity() {
		return identity;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getViolateCount() {
		return violateCount;
	}

	public void setViolateCount(Integer violateCount) {
		this.violateCount = violateCount;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public User() {
		super();
	}
	
	
	
//	@OneToMany(cascade =CascadeType.ALL,mappedBy = "userId")
//	private Integer userFav;
//	
//	@OneToMany(cascade =CascadeType.ALL,mappedBy = "userId")
//	private Integer restaurantList;
//	
//	@OneToMany(cascade =CascadeType.ALL,mappedBy = "userId")
//	private Integer shopkeeperInfo;
//	
//	@OneToMany(cascade =CascadeType.ALL,mappedBy = "userId")
//	private Integer restaurantComment;
//	
//	@OneToMany(cascade =CascadeType.ALL,mappedBy = "userId")
//	private Integer article;
//	
//	@OneToMany(cascade =CascadeType.ALL,mappedBy = "hashtagUser")
//	private Integer hashtag;
//	@OneToMany(cascade =CascadeType.ALL,mappedBy = "userId")
//	private Integer articleComment;
//	@OneToMany(cascade =CascadeType.ALL,mappedBy = "receiver")
//	private Integer social;
//	
//	@OneToMany(cascade =CascadeType.ALL,mappedBy = "sender")
//	private Integer social;

	

}
