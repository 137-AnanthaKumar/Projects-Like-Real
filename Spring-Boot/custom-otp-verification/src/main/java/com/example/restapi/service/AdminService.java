package com.example.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.restapi.dao.AdminRepo;
import com.example.restapi.entity.AdminEntity;
@Service
public class AdminService {
	
	@Autowired
	private AdminRepo repo;

	public AdminEntity createNewAdmin(AdminEntity adminEnt) {
		// TODO Auto-generated method stub
		AdminEntity entiDp=repo.save(adminEnt);
		return entiDp;
	}

	public AdminEntity getAdimn(long id) {
		AdminEntity ent=repo.findById(id);
		return ent;
	}

	public AdminEntity updateAdminById(long id, AdminEntity admin) {
				AdminEntity oldAdmin=repo.findById(id);
				System.out.println("age"+admin.getAge());
				System.out.println("age"+oldAdmin.getAge());

		oldAdmin.setAge(admin.getAge());
		oldAdmin.setName(admin.getName());
		AdminEntity updatedEntity=repo.save(oldAdmin);

		
		
		
		return updatedEntity;
	}

	

}
