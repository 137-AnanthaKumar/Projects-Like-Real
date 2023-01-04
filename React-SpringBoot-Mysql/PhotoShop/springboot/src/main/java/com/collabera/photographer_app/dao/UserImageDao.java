package com.collabera.photographer_app.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.collabera.photographer_app.dto.AdminAndUsers;
import com.collabera.photographer_app.dto.UserImages;

@Repository
public interface UserImageDao extends JpaRepository<UserImages, String> {

	UserImages findByImageId(String imageId);
	
	@Query(value = "SELECT * FROM user_images where user_id=?1", nativeQuery = true)
	Collection<UserImages> findAll(int photograoherId);

	@Query(value = "SELECT * FROM user_images where user_id=:photograoherId", nativeQuery = true)
	List<UserImages> getAllImages(int photograoherId);

}
