package com.example.restapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.restapi.entity.NewUser;

@Repository
public interface UserRepo extends JpaRepository<NewUser, Integer> {

	NewUser findByMobile(long mobile);
	NewUser findById(int id);
	
	
	@Query(value = "SELECT * FROM Newuser u WHERE u.active_sta ='Active' ", nativeQuery = true)
	List<NewUser> findAllActiveUsers();
	
	

}
