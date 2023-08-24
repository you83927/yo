package com.ispan.demo.model;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
	
	@Query("select u from User u where username like %:username%")
	List<User> findByUserNames(String username);
	
	@Query("select u from User u where u.username like %:username%")
	Page<User> findByUserNamesPage(String username,Pageable pageable);
	
	boolean existsByUsername(String username);
	
	byte[] findPhotoById(Integer id);  
	
//	  @Query("SELECT u FROM User as user JOIN u.userFavorites as uf JOIN uf.article a WHERE u.id = :userId")
//	    User findUserWithFavoriteArticles(@Param("userId") Integer userId);

//	@Query("select a,u from Article a INNER join UserFavorite uf ON a.id=uf.articleId INNER join User u ON uf.userId=u.id where u.id = :userId")
//	List<Object[]> findFavoriteArticleByUserId(Integer userId);
	
	//文章
	@Query("select u,a,u1 from User u INNER join Article a ON  a.userId=u.Id INNER join UserFavorite uf ON a.id=uf.articleId INNER join User u1 ON uf.userId=u1.id  where u1.id = :userId")
	List<Object[]> findFavoriteArticleByUserId(Integer userId);
	
	@Query("select u,a from User u INNER join Article a ON  a.userId=u.Id INNER join UserFavorite uf ON a.id=uf.articleId  where uf.userId=:userId AND a.title like %:title%")
	Page<Object[]> findFavoriteArticleByUsername(Integer userId,String title,Pageable pageable);
//			 select * from [User] u INNER join Article a ON  a.user_Id=u.Id INNER join User_Fav uf ON a.id=uf.article_Id where uf.user_id=1 and a.title like '%%'			
	//餐廳
	@Query("select r from RestaurantList r INNER join UserFavorite uf ON r.id=uf.restaurantId INNER join User u ON uf.userId=u.id where u.id = :userId")
	List<RestaurantList> findFavoriteRestaurantListByUserId(Integer userId);
	
	@Query("select r from RestaurantList r INNER join UserFavorite uf ON r.id=uf.restaurantId INNER join User u ON u.id=:userId where  r.name like %:name%")
	Page<RestaurantList> findFavoriteRestaurantListByRestaurantName(Integer userId, String name,Pageable pageable);
	
	//食物
	@Query("select f from FoodType f INNER join UserFavorite uf ON f.id=uf.foodId INNER join User u ON uf.userId=u.id where u.id = :userId")
	List<FoodType> findFavoriteFoodTypeByUserId(Integer userId);
	
	@Query("select f from FoodType f INNER join UserFavorite uf ON f.id=uf.foodId INNER join User u ON u.id=:userId where f.foodType like %:foodType%")
	Page<FoodType> findFavoriteFoodTypeByFoodType(Integer userId,String foodType,Pageable pageable);
	
//	@Query("select f from Follower f INNER JOIN User u ON f.following=u.id where u.id=:userId")
//	List<Follower> findFollowerByUserId(Integer userId);
	
//	 select * from [user]  INNER JOIN Follower  ON [user].id = follower.following WHERE follower.user_id = 1
	@Query("SELECT u FROM User u INNER JOIN Follower f ON u.id = f.id.following WHERE f.id.userId = :userId")
	Page<User> findFollowingUsersByUserId(Integer userId,Pageable pageable);
	
	@Query("SELECT u FROM User u INNER JOIN Follower f ON u.id = f.id.following WHERE f.id.userId = :userId AND f.id.following = :following")
	User findFollowingUsersByFollowing(Integer userId,Integer following);
	
	@Query("SELECT u FROM User u INNER JOIN Follower f ON u.id = f.id.following WHERE f.id.userId=:id AND u.username like %:username%")
	Page<User> findFollowingUsersByUserName(String username,Integer id,Pageable pageable);

//	@Query("select a,u from Article a INNER JOIN User u on a.userId=u.id where u.id=:userId AND a.title like %:title% ")
//	Page<Object[]> findArticleByUserId(Integer userId,String title,Pageable pageable);
	
	@Query("select a,u from Article a INNER JOIN User u on a.userId=u.id where u.id=:userId AND a.type=:type AND a.title like %:title% ")
	Page<Object[]> findArticleByUserId(Integer userId,String title,Integer type,Pageable pageable);
	
	

}
