package com.ispan.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ispan.demo.model.FollowRepository;
import com.ispan.demo.model.Follower;

@Service
public class FollowSrevice {

	@Autowired
	private FollowRepository followRepositiory;
	
	public List<Follower> findByUserId(Integer userId){
	List<Follower> list = followRepositiory.findByUserId(userId);
		 if(list.isEmpty()) {
			 return null;
		 }
		 return list;
	}
	
}
