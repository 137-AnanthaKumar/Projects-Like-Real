package com.love.logic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.love.logic.service.AdminUserService;



@RestController
@RequestMapping("/useradmin")
public class UserAdminController {
	
	
	@Autowired
	AdminUserService adminUserService;
	
//	@PostMapping("/newAdmin")
//	public ResponseEntity<?> createNewAdmin(@RequestBody User user){
//		user.setRole("ROLE_ADMIN");
//		Response response=adminUserService.createUser(user);
//		return  ResponseEntity.ok(response);
//		
//		
//	}
	
//	
//	@PutMapping("/lockseat")
//	public 

}
