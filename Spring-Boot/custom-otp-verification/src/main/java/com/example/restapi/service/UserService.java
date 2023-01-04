package com.example.restapi.service;

import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restapi.dao.UserRepo;
import com.example.restapi.entity.NewUser;
import com.example.restapi.exception.NoIdResponse;

@Service
public class UserService {
	 private static final org.jboss.logging.Logger Logger=LoggerFactory.logger(UserService.class);

    @Autowired
	private UserRepo repo;
    

	public boolean createNewUser(NewUser newuser) {
		
		NewUser user=repo.save(newuser);
		Logger.info("User Creted "+user.getName());
        return true;
	}
	
	
	
	public boolean isAvailable(long mobile) {
		System.out.println(mobile);
		NewUser user=new NewUser();
		user=repo.findByMobile(mobile);
		if (user==null) {
			return false;
		}
		return true;
	}

	public List<NewUser> getAllUsers() {
		return repo.findAll();
	}

	public NewUser getuserByMobile(long mobile) {
		// TODO Auto-generated method stub
		NewUser user=new NewUser();
		user=repo.findByMobile(mobile);
		return user;
	}



	public boolean isVerified(Integer id) {
		// TODO Auto-generated method stub
	
			NewUser user=repo.findById(id)
					.orElseThrow(() -> new NoIdResponse("Employee not exist with id :" + id));
			
			String mob=user.getMobileVerifyStatus();
			String mail=user.getMailVerifyStatus();

			if(((user.getMailVerifyStatus()).equalsIgnoreCase("Verified")) && (user.getMobileVerifyStatus()).equalsIgnoreCase("Verified") ) {
				user.setAccunt_sta("Active");
				repo.save(user);
				return true;
			}
			else {
				
				return false;
			}
		
		
		
}
	
//	public boolean isVerified(Integer id) {
//		// TODO Auto-generated method stub
//		try {
//			NewUser user=repo.getById(id);
//			String mob=user.getMobileVerifyStatus();
//			String mail=user.getMailVerifyStatus();
//
//			if(((user.getMailVerifyStatus()).equalsIgnoreCase("Verified")) && (user.getMobileVerifyStatus()).equalsIgnoreCase("Verified") ) {
//				user.setAccunt_sta("Active");
//				repo.save(user);
//				return true;
//			}
//			else {
//				
//				return false;
//			}
//		
//		
//		}
//		catch(Exception e){
//			return false;
//		}
		
	

		
		
		
		
		
	



	public List<NewUser> getAllactiveUsers() {
		// TODO Auto-generated method stub
		return repo.findAllActiveUsers();
	}
	
	
	
}
