package com.example.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restapi.dao.EmployeeRepo;
import com.example.restapi.entity.EmployeeEntity;
import com.example.restapi.entity.NewUser;

@Service
public class EmployeeService {
    @Autowired
	private EmployeeRepo repo;
	
	public EmployeeEntity createEmployee(EmployeeEntity emp) {
		
		emp=repo.save(emp);
		return emp;
		
		
	}

//	public EmployeeEntity getEmployeeByid(long id) { 
//		
//		return repo.getById(id) ;
//	}

	public EmployeeEntity getEmployeeByName(String name) {
		// TODO Auto-generated method stub
		return repo.findByName(name);
	}

	public List<EmployeeEntity> getAllUsers() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public EmployeeEntity updateById(String name, EmployeeEntity ent) {
		EmployeeEntity enti=repo.findByName(name);
		System.out.println("entit"+enti.getAge());
		enti.setAge(ent.getAge());
		enti.setName(ent.getName());
		enti.setPassword(ent.getPassword());
		enti.setEmail(ent.getEmail());
		repo.save(enti);
		return enti;
	}

}
