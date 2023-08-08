package com.ispan.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserFavoriteRepository extends JpaRepository<UserFavorite, Integer> {

	List<UserFavorite> findByUserId(Integer userId);

	List<UserFavorite> findByArticleId(Integer articleId);
	
	List<UserFavorite> findByRestaurantId(Integer restaurantId);
	
	List<UserFavorite> findByFoodId(Integer foodId);
	
	
	void deleteByArticleId(Integer articleId);

	void deleteByRestaurantId(Integer restaurantId);
	
	void deleteByFoodId(Integer foodId);
	
	
	boolean existsByUserId(Integer userId);

	boolean existsByArticleId(Integer articleId);
	
	boolean existsByRestaurantId(Integer restaurantId);
	
	boolean existsByFoodId(Integer foodId);
	
	
	@Query("SELECT uf.articleId FROM UserFavorite uf WHERE uf.userId = :userId AND uf.articleId IS NOT NULL")
    List<Integer> findArticleIdsByUserId(@Param("userId") Integer userId);

	@Query("SELECT uf.restaurantId FROM UserFavorite uf WHERE uf.userId = :userId AND uf.restaurantId IS NOT NULL")
    List<Integer> findRestaurantIdsByUserId(@Param("userId") Integer userId);
	
	@Query("SELECT uf.foodId FROM UserFavorite uf WHERE uf.userId = :userId AND uf.foodId IS NOT NULL")
    List<Integer> findFoodIdsByUserId(@Param("userId") Integer userId);
	
	
}
