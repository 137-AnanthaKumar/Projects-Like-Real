package com.example.restapi.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ServiceClass {
	
	@Autowired
	private Repo repo;

	public StudentEntity creatNew(StudentEntity stuEnt) {
		StudentEntity saveditems=repo.save(stuEnt);
		return saveditems;
	}

}
