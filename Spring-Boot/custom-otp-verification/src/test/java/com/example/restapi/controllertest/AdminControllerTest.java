package com.example.restapi.controllertest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.restapi.dao.AdminRepo;
import com.example.restapi.entity.AdminEntity;
import com.example.restapi.service.AdminService;
@SpringBootTest
class AdminControllerTest {
	
	@Autowired
	private AdminRepo repo;
	
	@Autowired
	private AdminService adminServ;
	

	@Test
	@Order(1)
	void test() {
//		AdminEntity adminEntity=repo.save(new AdminEntity("anandakila",19));
//		assertThat(adminEntity.getAge().(equals(19)));
		
		AdminEntity adminE=new AdminEntity();
		adminE.setAge(16);
		adminE.setName("AnandAkila");
		AdminEntity datafromDp=repo.save(adminE);
		assertEquals(datafromDp.getAge(),adminE.getAge());
		
	}
	@Test
	@Order(2)
	public void testCreate() {
		AdminEntity e=new AdminEntity();
		e.setAge(12);
		
		e.setName("swathi");
		
		AdminEntity storedValue=repo.save(e);
		AdminEntity ea=repo.findById(storedValue.getId()).get();
		assertEquals(ea.getAge(),e.getAge());
		// assertNotNull(repo.findById(1L).get());
		}
	
	@Test
	@Order(3)
	public void testDelete() {
		AdminEntity e=new AdminEntity();
		e.setAge(12);
	
		e.setName("suku");
		AdminEntity storedValue=repo.save(e);
		repo.deleteById(storedValue.getId());
		Boolean ent=repo.findById(storedValue.getId()).isPresent();
		System.out.println(ent);
		assertFalse(ent);

		}
//	@Test
//	@Order(4)
//	public void testUpdate() {
//		AdminEntity e=new AdminEntity();
//		e.setAge(12);
//		// e.setEmail("suku@gmail.com");
//		e.setName("suku");
//		// e.setPassword("123");
//		AdminEntity storedValue=repo.save(e);
//		AdminEntity ent=service.updateByName(e.getName(),e);
//		// repo.updateByName(storedValue.getName());
//		//EmployeeEntity nt=repo.findById(storedValue.getId()).get();
//		assertEquals(e.getAge(),ent.getAge());
//		System.out.println(ent);
//
//
//		}

}
