package com.gallary.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gallary.model.Photos;

@Repository
public interface PhotoRepo extends JpaRepository<Photos, Long>{
	
	public List<Photos> findByOrderByIdDesc();

}
