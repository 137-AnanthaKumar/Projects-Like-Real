package com.example.restapi.mapping;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/onetoone")
public class Controller {
	
	@Autowired
	private ServiceClass servi;
	@PostMapping("/create")
	public ResponseEntity<?> createNew(@RequestBody StudentEntity stuEnt){
		
		StudentEntity cretedd=servi.creatNew(stuEnt);
		
		return new ResponseEntity<StudentEntity>(cretedd,HttpStatus.OK);
		
	}

}
