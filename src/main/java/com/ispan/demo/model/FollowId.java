package com.ispan.demo.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FollowId implements Serializable {

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "following")
	private Integer following;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getFollowing() {
		return following;
	}

	public void setFollowing(Integer following) {
		this.following = following;
	}

	public FollowId() {
		super();
	}

	   @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        FollowId followId = (FollowId) o;
	        return Objects.equals(userId, followId.userId) &&
	                Objects.equals(following, followId.following);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(userId, following);
	    }

		@Override
		public String toString() {
			return "FollowId [userId=" + userId + ", following=" + following + "]";
		}
	
	    
	
}
