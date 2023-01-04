package com.dream11.fantasy.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream11.fantasy.model.MyTeam;
import com.dream11.fantasy.model.UserAccount;
import com.dream11.fantasy.model.UserEntity;
import com.dream11.fantasy.service.UserAccountService;


@CrossOrigin("*")
@RestController
@RequestMapping("/dream11Account")
public class UserAccountController {
	
	@Autowired
	private UserAccountService userActService;

	@GetMapping("getUser/{userId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('CONTROLLER')")
    public UserEntity getUserAccountDetail(@PathVariable long userId){
		UserEntity us=new UserEntity();

		UserEntity userEnt=userActService.getUserById(userId);
		
		return userEnt;
		
	}
	
	@GetMapping("getmyAccountTeams/{accountName}/{contestCode}")
	@PreAuthorize("hasRole('USER')")
    public List<MyTeam> getMyAccountTeams(@PathVariable String accountName,@PathVariable String contestCode){
		System.out.println("I am Here Thanga");
		System.out.println("ActNam "+accountName+" "+contestCode);
		List<MyTeam> myteams=userActService.getMyAccountTeams(accountName,contestCode);
		return myteams;
		
	}
	
	
	
	
}
