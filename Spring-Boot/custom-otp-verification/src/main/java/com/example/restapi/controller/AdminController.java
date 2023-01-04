package com.example.restapi.controller;

import java.util.Optional;

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

import com.example.restapi.dao.AdminRepo;
import com.example.restapi.entity.AdminEntity;
import com.example.restapi.exception.ReSourseNotFound;
import com.example.restapi.service.AdminService;
@CrossOrigin(origins ="http://localhost:3000")

@RestController
@RequestMapping("/admin")
public class AdminController {
	 private static final org.jboss.logging.Logger Logger=LoggerFactory.logger(AdminController.class);
	@Autowired
	private AdminService adminServ;
	
	@Autowired
	private AdminRepo repo;
	
	@PostMapping("/newadmin")
	public ResponseEntity<AdminEntity> createNewAdmin(@RequestBody AdminEntity adminEnt){
		AdminEntity admin=adminServ.createNewAdmin(adminEnt);
		Logger.info("New Admin Created With This Id"+admin.getId());
		return new ResponseEntity<AdminEntity>(admin, HttpStatus.OK);
		
	}
	

	@GetMapping("/getAdmin/{id}")
	public AdminEntity getAdmin(@PathVariable long id){
		AdminEntity entity=adminServ.getAdimn(id);
		if(entity==null) {
			Logger.warn("Details Not Available");
			throw new ReSourseNotFound();
		}
		else {
			Logger.info(entity.getName());
			return entity;
		}
		
		
	}
	
	
	@PutMapping("updateAdmin/{id}")
	public ResponseEntity<AdminEntity> upDateAdmin(@PathVariable long id,@RequestBody AdminEntity admin){
		AdminEntity updatedAdminEntity=adminServ.updateAdminById(id,admin);
		
		return ResponseEntity.status(HttpStatus.OK).body(updatedAdminEntity);
		
	}


}

