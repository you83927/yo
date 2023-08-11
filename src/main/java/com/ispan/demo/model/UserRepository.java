package com.ispan.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String username);
	
	boolean existsByUserName(String username);
	
	byte[] findPhotoById(Integer id); 
	
//	  @Query("SELECT u FROM User as user JOIN u.userFavorites as uf JOIN uf.article a WHERE u.id = :userId")
//	    User findUserWithFavoriteArticles(@Param("userId") Integer userId);

	@Query("select a from Article a INNER join UserFavorite uf ON a.id=uf.articleId INNER join User u ON uf.userId=u.id where u.id = :userId")
	List<Article> findFavoriteArticleByUserId(Integer userId);
	
	@Query("select r from RestaurantList r INNER join UserFavorite uf ON r.id=uf.restaurantId INNER join User u ON uf.userId=u.id where u.id = :userId")
	List<RestaurantList> findFavoriteRestaurantListByUserId(Integer userId);
	
	@Query("select f from FoodType f INNER join UserFavorite uf ON f.id=uf.foodId INNER join User u ON uf.userId=u.id where u.id = :userId")
	List<FoodType> findFavoriteFoodTypeByUserId(Integer userId);
	
	
}
