package com.example.irctc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.irctc.model.AccountDetails;
@Repository
public interface AccountDetailsRepo extends JpaRepository<AccountDetails, Long> {
	
	Boolean existsByMobile(String mobile);
	
	Boolean existsByAuthorno(String authorno);

}
