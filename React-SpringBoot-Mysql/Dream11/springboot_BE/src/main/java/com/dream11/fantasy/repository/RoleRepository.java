package com.dream11.fantasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream11.fantasy.model.ERole;
import com.dream11.fantasy.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(ERole roleAdmin);

}
