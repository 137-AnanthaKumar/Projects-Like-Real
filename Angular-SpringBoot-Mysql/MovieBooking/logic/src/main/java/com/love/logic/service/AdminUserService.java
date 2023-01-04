package com.love.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.love.logic.models.User;
import com.love.logic.payload.response.Response;
import com.love.logic.repository.UserRepository;



@Service
public class AdminUserService {

	@Autowired
	UserRepository userRepo;
	
//	public Response createUser(User user) {
//		Response res;
//		if(userRepo.findByUserName(user.getUserName()) || userRepo.findByMobile(user.getMobile())) {
//			res=new Response("alredyFound",true);
//		}
//		else {
//			user =userRepo.save(user);
//			res=new Response("created",false);
//			
//		}
//		
//		return null;
//	}

}
