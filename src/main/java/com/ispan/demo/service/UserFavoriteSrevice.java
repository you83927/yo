package com.ispan.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ispan.demo.model.UserFavorite;
import com.ispan.demo.model.UserFavoriteRepository;

@Service
public class UserFavoriteSrevice {

	@Autowired
	private UserFavoriteRepository userFavoriteRepository;
	
	public UserFavorite findById(Integer id) {
		Optional<UserFavorite> optional = userFavoriteRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
		
	}
	
}
