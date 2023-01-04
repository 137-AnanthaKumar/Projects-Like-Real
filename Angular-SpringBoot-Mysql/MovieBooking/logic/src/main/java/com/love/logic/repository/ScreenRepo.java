package com.love.logic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.love.logic.models.ScreenDetails;



@Repository
public interface ScreenRepo extends JpaRepository<ScreenDetails, Integer> {
	@Query(value="SELECT screen_id FROM screen  ",nativeQuery=true)
	List<Integer> getAllScreenNumber();

}
