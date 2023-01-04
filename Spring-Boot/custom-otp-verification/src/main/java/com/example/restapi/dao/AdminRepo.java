package com.example.restapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restapi.entity.AdminEntity;
import com.example.restapi.entity.EmployeeEntity;
@Repository
public interface AdminRepo extends JpaRepository<AdminEntity, Long> {
	AdminEntity findById(long id);
}
