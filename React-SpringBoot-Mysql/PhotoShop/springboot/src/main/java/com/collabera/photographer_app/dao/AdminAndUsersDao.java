package com.collabera.photographer_app.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.collabera.photographer_app.dto.AdminAndUsers;

@Repository
public interface AdminAndUsersDao extends JpaRepository<AdminAndUsers, Integer> {
	
	AdminAndUsers findById(int id);
	
	 @Query("from AdminAndUsers where userName=?1")
	AdminAndUsers findByUserName(String userName);
	
	
   @Query("from AdminAndUsers where password=?1")
	AdminAndUsers findByPassword(String password);
   @Query("from AdminAndUsers where role=?1")
	List<AdminAndUsers> findByRole(String role);
   

	@Query(value = "SELECT * FROM admin_and_users where role='ROLE_photographer'", nativeQuery = true)
         List<AdminAndUsers> findAllPhotographers();
	
	 @Query("from AdminAndUsers where category=?1")
	List<AdminAndUsers> findByCategory(String category);

	@Query(value = "SELECT id FROM admin_and_users where user_name=:username and password=:password", nativeQuery = true)
	String getUserId(String username, String password);

}
