package com.ispan.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

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

	@Column(name = "username", columnDefinition = "nvarchar(15)", unique = true)
	private String username;

	@Column(name = "password", columnDefinition = "nvarchar(15)")
	private String password;

	@Column(name = "[identity]")
	private Integer identity;

	@Column(name = "nickname", columnDefinition = "nvarchar(25)")
	private String nickname;

	@Lob
	@Column(name = "photo", columnDefinition = "image")
	private byte[] photo;

	@Column(name = "gender", columnDefinition = "smallint")
	private Integer gender;

	@Column(name = "email", columnDefinition = "nvarchar(225)")
	private String email;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birthday", columnDefinition = "date")
	private LocalDate birthday;

	@Column(name = "status")
	private Boolean status;

	@Column(name = "violate_count")
	private Integer violateCount;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "modified_date", columnDefinition = "datetime")
	private LocalDateTime modifiedDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "created_date", columnDefinition = "datetime")
	private LocalDateTime createdDate;

	@Column(name = "modified_by", columnDefinition = "sysname")
	private String modifiedBy;
	
//	  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//	    private List<UserFavorite> userFavorites = new ArrayList<>();

	@PrePersist
	public void onCreate() {
		if (createdDate == null) {
			createdDate = LocalDateTime.now();
		}
		modifiedDate = createdDate;
		modifiedBy = getUsername();

//		modifiedBy = getCurrentUsername();
	}

	@PreUpdate
	public void onUpdate() {
		modifiedDate = LocalDateTime.now();
//		modifiedBy = getCurrentUsername();
	}

//	private String getCurrentUsername() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication == null || !authentication.isAuthenticated()) {
//			return "anonymousUser";
//		}
//		return authentication.getName();
//	}

	

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
