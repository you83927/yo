package com.ispan.demo.model;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String username);
	
	@Query("select u from User u where userName like %:username%")
	List<User> findByUserNames(String username);
	
	@Query("select u from User u where userName like %:username%")
	Page<User> findByUserNamesPage(String username,Pageable pageable);
	
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
	
//	@Query("select f from Follower f INNER JOIN User u ON f.following=u.id where u.id=:userId")
//	List<Follower> findFollowerByUserId(Integer userId);
	
//	 select * from [user]  INNER JOIN Follower  ON [user].id = follower.following WHERE follower.user_id = 1
	@Query("SELECT u FROM User u INNER JOIN Follower f ON u.id = f.id.following WHERE f.id.userId = :userId")
	Page<User> findFollowingUsersByUserId(Integer userId,Pageable pageable);
	
	@Query("SELECT u FROM User u INNER JOIN Follower f ON u.id = f.id.following WHERE f.id.following = :following")
	User findFollowingUsersByFollowing(Integer following);
	
	@Query("SELECT u FROM User u INNER JOIN Follower f ON u.id = f.id.following WHERE u.userName like %:username%")
    List<User> findFollowingUsersByUserName(String username);




}
