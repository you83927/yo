package com.ispan.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="follower")
public class Follower {

	@EmbeddedId
	private FollowId id;
	
	@Column(name="is_fav")
	private Boolean isFav;

	

	public FollowId getId() {
		return id;
	}

	public void setId(FollowId id) {
		this.id = id;
	}

	public Boolean getIsFav() {
		return isFav;
	}

	public void setIsFav(Boolean isFav) {
		this.isFav = isFav;
	}

	public Follower() {
		super();
	}
	
	
	
	
}
