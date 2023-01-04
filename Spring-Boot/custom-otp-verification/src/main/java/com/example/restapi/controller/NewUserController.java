package com.example.restapi.controller;

import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
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

import com.example.restapi.dao.UserRepo;
import com.example.restapi.entity.NewUser;
import com.example.restapi.exception.ReSourseNotFound;
import com.example.restapi.service.UserService;


@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/user")
public class NewUserController {
	
	 private static final org.jboss.logging.Logger Logger=LoggerFactory.logger(AdminController.class);

	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepo repo;
	
	@PostMapping(value="/newuserregister", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createNewUser(@RequestBody NewUser newuser){
		 
			if(!service.isAvailable(newuser.getMobile())) {
				boolean status=service.createNewUser(newuser);
				if (status) {
					Logger.info("User Created ");
					return ResponseEntity.status(HttpStatus.CREATED).body("User created");
				}
				else {
					return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("UserNotCreated");

				}
			}
			else {
				return ResponseEntity.ok("Created");
			}
}
	
	
	@GetMapping
	public List<NewUser> getAllUser(){
	
		return service.getAllUsers();
}
	
	
	@GetMapping("/getuser/{mobile}")
	public ResponseEntity<NewUser> getUserById(@PathVariable long mobile){
		NewUser user=new NewUser();
		user=service.getuserByMobile(mobile);
	
		if(user!=null) {
			return ResponseEntity.ok(user);
		}
		else
		{
		  throw new ReSourseNotFound();
         
		}
		
		
	}
	
	@PutMapping("/activate/{id}")
	public ResponseEntity<String> activateAccount(@PathVariable Integer id){
		
		if(service.isVerified(id)) {
			return ResponseEntity.ok("Your Accont Activated");
		}
		return ResponseEntity.ok("your Email or Mobile Not Verified Still Now");
		
	}
	
	
	
	
	
	
	
	
 
}
