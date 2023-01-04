package com.dream11.fantasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dream11.fantasy.model.UserAccount;

@Repository
public interface UserAccountRepo extends JpaRepository<UserAccount, Integer> {

	boolean existsByAccountName(String accountName);

}
