package com.ispan.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String username);
	
	boolean existsByUserName(String username);
	
	byte[] findPhotoById(Integer id);
	
 
}
