package com.ispan.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follower, Integer> {

	
    @Query("SELECT f FROM Follower f WHERE f.id.userId = :userId")
    List<Follower> findByUserId(Integer userId);
	
}
