package com.ispan.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ispan.demo.model.UserFavorite;
import com.ispan.demo.model.UserFavoriteRepository;

import jakarta.transaction.Transactional;

@Service
public class UserFavoriteService {

	@Autowired
	private UserFavoriteRepository userFavoriteRepository;

	// 以Id搜尋
	public UserFavorite findById(Integer id) {
		Optional<UserFavorite> optional = userFavoriteRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	// 以userId搜尋
	public List<UserFavorite> findByUserId(Integer userId) {
		List<UserFavorite> userFavorite = userFavoriteRepository.findByUserId(userId);
		if (userFavorite != null) {
			return userFavorite;
		}
		return null;
	}

	// 以articleId搜尋
	public List<UserFavorite> findByArticleId(Integer articleId) {
		List<UserFavorite> userFavorite = userFavoriteRepository.findByArticleId(articleId);
		if (userFavorite != null) {
			return userFavorite;
		}
		return null;
	}

	// 以restaurantId搜尋
	public List<UserFavorite> findByRestaurantId(Integer restaurantId) {
		List<UserFavorite> userFavorite = userFavoriteRepository.findByRestaurantId(restaurantId);
		if (userFavorite != null) {
			return userFavorite;
		}
		return null;
	}

	// 以foodId搜尋
	public List<UserFavorite> findByFoodId(String foodId) {
		List<UserFavorite> userFavorite = userFavoriteRepository.findByFoodId(foodId);
		if (userFavorite != null) {
			return userFavorite;
		}
		return null;
	}

	// 新增使用者最愛
	public UserFavorite insertUserFavorite(UserFavorite userFavorite) {
		boolean existsByArticleId = userFavoriteRepository.existsByArticleId(userFavorite.getArticleId());

		boolean existsByRestaurantId = userFavoriteRepository.existsByRestaurantId(userFavorite.getRestaurantId());
		boolean existsByFoodId = userFavoriteRepository.existsByFoodId(userFavorite.getFoodId());

		if (existsByArticleId && existsByRestaurantId && existsByFoodId) {
			return null;
		}
		return userFavoriteRepository.save(userFavorite);

	}

	// 以id刪除
	public void deleteUserById(Integer id) {
		userFavoriteRepository.deleteById(id);
	}

	// 以articleId刪除
	public void deleteUserByArticleId(Integer articleId) {
		userFavoriteRepository.deleteByArticleId(articleId);
	}

	// 以restaurantId刪除
	public void deleteUserByRestaurantId(Integer restaurantId) {
		userFavoriteRepository.deleteByRestaurantId(restaurantId);
	}
	// 以foodId刪除
	public void deleteUserByFoodId(String foodId) {
		userFavoriteRepository.deleteByFoodId(foodId);
	}
	
	//以articleId搜尋not null的值
	public List<Integer> findArticleIdsByUserId(Integer userId) {
		return userFavoriteRepository.findArticleIdsByUserId(userId);
	}
	
	//以restaurantId搜尋not null的值
	public List<Integer> findRestaurantIdsByUserId(Integer userId) {
		return userFavoriteRepository.findRestaurantIdsByUserId(userId);
	}

	//以foodId搜尋not null的值
	public List<String> findFoodIdsByUserId(Integer userId) {
		return userFavoriteRepository.findFoodIdsByUserId(userId);
	}


}
