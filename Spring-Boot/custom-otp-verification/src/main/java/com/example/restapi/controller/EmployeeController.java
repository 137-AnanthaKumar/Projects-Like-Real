package com.example.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.dao.EmployeeRepo;
import com.example.restapi.entity.EmployeeEntity;
import com.example.restapi.entity.NewUser;
import com.example.restapi.service.EmployeeService;
import com.example.restapi.service.UserService;
@CrossOrigin(origins ="http://localhost:3000")

@RestController 
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmployeeRepo repo;

	@PostMapping("/create")
	public ResponseEntity<String> createEmployee(@RequestBody EmployeeEntity emp){
		
		  service.createEmployee(emp);
		  return ResponseEntity.status(HttpStatus.CREATED).body("Employee created");
		
//		return ResponseEntity.ok("employee creted");
		
	}
	
	@GetMapping("/getemp/{name}")
	public ResponseEntity<EmployeeEntity> getEmplyee(@PathVariable String name) {
		System.out.println("hello");
		EmployeeEntity entity=service.getEmployeeByName(name);
		if(entity==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entity);
		}
		else {
			return ResponseEntity.ok(entity);
		}
		
		
		
	}
	
	@GetMapping
	public List<EmployeeEntity> getAllUser(){
	

		return repo.findAll();
		
		
	}
	
	@PutMapping("/update/{name}")
	public EmployeeEntity upDateEmp(@RequestBody EmployeeEntity ent,@PathVariable String name  ){
		
		
		return service.updateById(name,ent);
		
	}


	
	
	
	@GetMapping("/activeusers")
	public List<NewUser> getActiveUser() {
		
		
		return userService.getAllactiveUsers();
		
	}
	
	
	
}
