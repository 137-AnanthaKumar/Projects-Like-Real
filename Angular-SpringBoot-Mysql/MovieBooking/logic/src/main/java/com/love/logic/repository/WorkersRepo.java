package com.love.logic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.love.logic.models.workersDetails;

@Repository
public interface WorkersRepo extends JpaRepository<workersDetails, Integer>{

	
	 @Query(value="SELECT MAX(worker_code)  FROM workers ",nativeQuery=true)
	 int getBigestCode();
	 
	 @Query(value="SELECT account_status FROM workers WHERE user_id=:id  ",nativeQuery = true)
	  String workerAccountStatus(Long id);

	 @Query(value="SELECT * FROM workers WHERE account_status='YETTOACTIVATE' or account_status='ACTIVE' or account_status='BLOCKED' ",nativeQuery = true)
	 List<workersDetails> getAllActiveEmployee();

	 @Query(value="SELECT worker_code FROM workers WHERE user_id=:id  ",nativeQuery = true)
	 int getWorkerCode(Long id);

	 @Query(value="SELECT worker_code FROM workers INNER JOIN users ON users.id=workers.user_id where users.username=:name   ",nativeQuery = true)
	 int getWorkerCodeByName(String name);

	workersDetails findByUserId(Long id);

//	 SELECT column_name(s)
//	 FROM table1
//	 INNER JOIN table2
//	 ON table1.column_name = table2.column_name;
//	 @Query(value="SELECT EXISTS(SELECT * FROM workers WHERE account_status='ACTIVE'or account_status='ACTIVE'  ",nativeQuery = true)
//	 Boolean workerAccountStatus(Long id);
	 
	 

}
