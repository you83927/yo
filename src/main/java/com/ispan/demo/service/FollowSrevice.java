package com.ispan.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ispan.demo.model.FollowRepository;
import com.ispan.demo.model.Follower;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FollowSrevice {

	@Autowired
	private FollowRepository followRepositiory;

	public List<Follower> findByFollowing(Integer following) {
		List<Follower> list = followRepositiory.findByFollowing(following);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	public void deleteFollowerByFollowing(Integer following) {
		followRepositiory.deleteByFollowing(following);
	}

	public Follower checkIfFollowingExists (Integer userId,Follower following) { 
		List<Follower> list = followRepositiory.findByUserId(userId);
		Integer followingUser =null ;
		for(Follower follower : list) {
			followingUser = follower.getId().getFollowing();
				if(followingUser == following.getId().getFollowing()) {
				return  null;
				}
				
		}
		followRepositiory.save(following);
		return following;
	}

}
