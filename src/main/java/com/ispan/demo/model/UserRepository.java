package com.ispan.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String username);
	
	boolean existsByUserName(String username);
	
	byte[] findPhotoById(Integer id);
	
//	  @Query("SELECT u FROM User u JOIN u.userFavorites uf JOIN uf.article a WHERE u.id = :userId")
//	    User findUserWithFavoriteArticles(@Param("userId") Integer userId);

	
 
}
