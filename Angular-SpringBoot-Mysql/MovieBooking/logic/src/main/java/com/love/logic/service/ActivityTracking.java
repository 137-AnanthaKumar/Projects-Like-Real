package com.love.logic.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.love.logic.models.ERole;
import com.love.logic.models.Role;
import com.love.logic.models.Tracking;
import com.love.logic.models.User;
import com.love.logic.repository.TrackingRepo;
import com.love.logic.repository.UserRepository;

@Service
public class ActivityTracking {
	
	@Autowired
	TrackingRepo trace;
	
	@Autowired
	UserRepository userrepo;
	
	

	public void trackActivity(String work, int workerCode, String name) {
		
		User user=userrepo.findByUsername(name).get();
		ERole role = null;
		
		Set<Role> roles = user.getRoles();
		   
		if(user!=null) {
			 List<Role> aList= roles.stream().collect(Collectors.toList());
			 role=aList.get(0).getName();
			
		}
		String a=workerCode+"";
		Tracking tracking=new Tracking(LocalTime.now(),LocalDate.now(),work,a,role);
		trace.save(tracking);
		
		switch (work) {
		case "NEWADMIN":
			
			break;
       case "BLOCKADMIN":
			
			break;
       case "ACTIVATEADMIN":
			
			break;
	   default:
			break;
		}
		
		
	}
	
	

}
