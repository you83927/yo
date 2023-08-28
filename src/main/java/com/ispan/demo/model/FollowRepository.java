package com.ispan.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follower, FollowId> {

	
    @Query("SELECT f FROM Follower f WHERE f.id.following = :following")
    List<Follower> findByFollowing(Integer following);
    
    @Modifying
    @Query("DELETE FROM Follower f WHERE f.id.following = :following")
    void deleteByFollowing(@Param("following") Integer following);
	
    @Query("SELECT f FROM Follower f WHERE f.id.userId = :userId")
    List<Follower> findByUserId(Integer userId);
}
