package com.example.restapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.restapi.dao.AdminRepo;
import com.example.restapi.entity.AdminEntity;

@SpringBootTest
class RestapiApplicationTests {

	
	@Autowired
	private AdminRepo repo;
	
	@Test
	void contextLoads() {
	}
	@Test
	public void testCreateAdmin() {
		AdminEntity adminEntity=repo.save(new AdminEntity("anandakila",19));
		repo.save(adminEntity);
		
	}

}
