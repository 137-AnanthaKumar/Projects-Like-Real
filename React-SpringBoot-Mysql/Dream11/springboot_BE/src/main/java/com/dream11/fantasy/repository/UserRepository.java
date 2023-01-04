package com.dream11.fantasy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream11.fantasy.model.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

	  Optional<UserEntity> findByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	

}
