package com.dream11.fantasy.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream11.fantasy.model.MyTeam;
import com.dream11.fantasy.model.UserAccount;
import com.dream11.fantasy.model.UserEntity;
import com.dream11.fantasy.repository.MyTeamRepo;
import com.dream11.fantasy.repository.UserAccountRepo;
import com.dream11.fantasy.repository.UserRepository;



@Service
public class UserAccountService {
	
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserAccountRepo repo;
	
	@Autowired
	private UserRepository repoUserEntity;
	
	@Autowired
	private MyTeamRepo myteamRepo;

	public boolean alreadyAvaiable(String accountName, String mail) {
		boolean availStatus=repo.existsByAccountName(accountName);
		boolean availStatusForMail=repoUserEntity.existsByEmail(mail);
		if(availStatus || availStatusForMail ){
			return true;
		}
		else if(!availStatus && !availStatusForMail ){
			return false;
		}
		else return true;
	}

	public UserAccount createAccount(UserAccount userAccount) {
		
		return repo.save(userAccount);
	}


	public UserEntity getUserById(long userId) {
		// TODO Auto-generated method stub
		return userRepo.getById(userId);
	}

	public List<MyTeam> getMyAccountTeams(String accountName, String contestCode) {
		// TODO Auto-generated method stub
		return myteamRepo.getMyAccountTeams(accountName,contestCode);
	}

	

}
