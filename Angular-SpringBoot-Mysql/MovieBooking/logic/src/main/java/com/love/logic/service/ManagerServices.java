package com.love.logic.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.love.logic.models.ERole;
import com.love.logic.models.Role;
import com.love.logic.models.Tracking;
import com.love.logic.models.User;
import com.love.logic.models.workersDetails;
import com.love.logic.payload.request.UpdateRequest;
import com.love.logic.payload.response.AllWorkerResponse;
import com.love.logic.payload.response.MessageResponse;
import com.love.logic.repository.RoleRepository;
import com.love.logic.repository.TrackingRepo;
import com.love.logic.repository.UserRepository;
import com.love.logic.repository.WorkersRepo;

import lombok.experimental.Helper;

@Service
public class ManagerServices {

	
	@Autowired
	WorkersRepo workerrepo;
	
	 @Autowired
	  UserRepository userRepository;

	  @Autowired
	  RoleRepository roleRepository;
	  
	  @Autowired
	   com.love.logic.helper.Helper helper;
	  

	  @Autowired
	  PasswordEncoder encoder;
	  
	  @Autowired
	  ActivityTracking tracking;
	  
	  @Autowired
	  TrackingRepo trackingRepo;
	  
	
	
	
//	public MessageResponse createNewAdmin(workersDetails worker,String name) {
//		
//		
//		
//		if(userDetailValidation(worker.getUser()).equalsIgnoreCase("FINE")) {
//			
//			int lastCode=workerrepo.getBigestCode();
//			System.out.println("hii"+ lastCode);
//			worker.setWorkerCode(lastCode+1);
//			//worker.setWorkerCode(1012);
//			worker.setFromdate(LocalDate.now());
//			String pass=helper.password(6);
//			worker.setFirstPass(pass);
//			//worker.getUser().setPassword(encoder.encode(pass));
//			//worker.getUser().setPassword(helper.password(6));
//			 Set<Role> roles = new HashSet<>();
//			 Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found."));;
//			  roles.add(userRole);
//			  User user = new User(worker.getUser().getUsername(), 
//					  worker.getUser().getEmail(),
//		              
//		               
//		               encoder.encode(pass));
//			 
//			 user.setRoles(roles);
//			User uu= userRepository.save(user);
//			//userRepository.save(worker.getUser());
//			
//		    try {
//		    	worker.setUser(uu);
//				workerrepo.save(worker);
//				
//				}
//				catch(Exception e) {
//					return new MessageResponse("SOMETHING WRONG TRY AGAIN");
//				}
//		    
//		    tracking.trackActivity("NEWADMIN",worker.getWorkerCode(),name);
//			
//			return new MessageResponse("NEW WORKER ADDED : Password - "+pass);
//	     }
//		else {
//			return new MessageResponse(userDetailValidation(worker.getUser()));
//		}
//		
//		}
	
	
  public MessageResponse createNewWorker(workersDetails worker,String name,String role) {
		
		
		
		if(userDetailValidation(worker.getUser()).equalsIgnoreCase("FINE")) {
			
			int lastCode=workerrepo.getBigestCode();
//			int lastCode=1012;
			System.out.println("hii"+ lastCode);
			worker.setWorkerCode(lastCode+1);
			//worker.setWorkerCode(1012);
			worker.setFromdate(LocalDate.now());
			String pass=helper.password(6);
			worker.setFirstPass(pass);
	        User user = new User(worker.getUser().getUsername(), 
					  worker.getUser().getEmail(),
		               encoder.encode(pass));
			 
			 user.setRoles(getRoleFromDp(role));
			User uu= userRepository.save(user);
			//userRepository.save(worker.getUser());
			
		    try {
		    	worker.setUser(uu);
				workerrepo.save(worker);
				
				}
				catch(Exception e) {
					return new MessageResponse("SOMETHING WRONG TRY AGAIN");
				}
		    
		    tracking.trackActivity("NEWADMIN",worker.getWorkerCode(),name);
			
			return new MessageResponse("NEW WORKER ADDED : Password - "+pass);
	     }
		else {
			return new MessageResponse(userDetailValidation(worker.getUser()));
		}
		
		}
	
	private Set<Role> getRoleFromDp(String role) {
		Set<Role> roles = new HashSet<>();
		
		   
		switch (role) {
			case "ROLE_MANAGER":
				roles.add(roleRepository.findByName(ERole.ROLE_MANAGER).orElseThrow(() -> new RuntimeException("Error: Role is not found."))) ;
				 break;
         case "ROLE_ADMIN":
        	 roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
				break;
         case "ROLE_MANAGER_BLOCKED":
				roles.add(roleRepository.findByName(ERole.ROLE_MANAGER_BLOCKED).orElseThrow(() -> new RuntimeException("Error: Role is not found."))) ;
				 break;
      case "ROLE_ADMIN_BLOCKED":
     	 roles.add(roleRepository.findByName(ERole.ROLE_ADMIN_BLOCKED).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
				break;
      case "ROLE_OWNER":
			roles.add(roleRepository.findByName(ERole.ROLE_OWNER).orElseThrow(() -> new RuntimeException("Error: Role is not found."))) ;
        }
		return roles;
	
}

	public String userDetailValidation(User user) {
		
		
		 if (userRepository.existsByUsername(user.getUsername())) {
		      return "USERNAME_AVAIL";
		    }

		  if (userRepository.existsByEmail(user.getEmail())) {
		      return "EMAIL_AVAIL";
		   }
		  else{
			  return "FINE";
		  }
	
	}

	public MessageResponse blockAdmin(Integer workerCode,String name) {
		
		workersDetails worker=workerrepo.getById(workerCode);
		 
		System.out.println(worker.getUser().getRoles());
	
	
	
		
		worker.setAccountStatus("BLOCKED");
		
		System.out.println(worker.getUser().getRoles());
		   changeRole(worker);
		workerrepo.save(worker);
		 tracking.trackActivity("BLOCKADMIN",worker.getWorkerCode(),name);
		 
		return new MessageResponse(worker.getFullName()+" : "+workerCode+" ACCOUNT DEACTIVATED");
	}
	
   private void changeRole(workersDetails worker) {
		String currentRole=null;
		
		
		
		String newRole="";
		for(Role ro:worker.getUser().getRoles()) {
			currentRole=ro.getName().toString();
		}
		
		if(currentRole.equalsIgnoreCase("ROLE_ADMIN")) {
			newRole="ROLE_ADMIN_BLOCKED";
		}
		if(currentRole.equalsIgnoreCase("ROLE_MANAGER")) {
			newRole="ROLE_MANAGER_BLOCKED";
		}
		
		if(currentRole.equalsIgnoreCase("ROLE_ADMIN_BLOCKED")) {
			newRole="ROLE_ADMIN";
		}
		if(currentRole.equalsIgnoreCase("ROLE_MANAGER_BLOCKED")) {
			newRole="ROLE_MANAGER";
		}
		
		
		
		if(currentRole.equalsIgnoreCase("ROLE_OWNER")) {
			newRole="ROLE_OWNER";
			worker.setAccountStatus("ACTIVE");
		}
		Set<Role> roleObj=getRoleFromDp(newRole);
		
		worker.getUser().setRoles(roleObj);
		
		
	}

public MessageResponse unblockAdmin(Integer workerCode,String name) {
		
		workersDetails worker=workerrepo.getById(workerCode);
		worker.setAccountStatus("ACTIVE");
		changeRole(worker);
        workerrepo.save(worker);
        tracking.trackActivity("ACTIVATEADMIN",worker.getWorkerCode(),name);
	    return new MessageResponse(worker.getFullName()+" : "+workerCode+" ACCOUNT ACTIVATED");
	
   }

public MessageResponse makeNewManger(workersDetails worker, String name) {
	  
	
	  return null;
}

public boolean accountStatus(Long id) {
	
	String a=workerrepo.workerAccountStatus(id);
	
	switch (a) {
	case "ACTIVE":
		
		return true;
		
    case "YETTOACTIVATE":
		
    	return true;
		
    case "BLOCKED":
		
    	return false;

	default:
		break;
	}
	
	return false;
}

public MessageResponse getRole(String name) {
	System.out.println("ROlE");
	User user=userRepository.findByUsername(name).get();
	
	workersDetails details=workerrepo.findByUserId(user.getId());
	int a=workerrepo.getWorkerCode(user.getId());
	
	
	ERole role = null;
	Set<Role> roles=user.getRoles();
	for(Role rol:roles) {
		role=rol.getName();
		System.out.println(rol.getName());
		break;
		
	}
	//System.out.println(roles.);
	//System.out.println("229"+.getName());
	System.out.println(details.getAccountStatus());
	return new MessageResponse(role.toString(),a,details.getAccountStatus());
}

 public List<AllWorkerResponse> getAllEmployee() {
		
  List<workersDetails> workers=workerrepo.getAllActiveEmployee();
  List<AllWorkerResponse> workersfi=new ArrayList<AllWorkerResponse>();
  for(workersDetails worker:workers) {
	  AllWorkerResponse obj=new AllWorkerResponse(worker.getWorkerCode(),worker.getFullName(),worker.getDoorNo(),worker.getArea(),worker.getMobileNo(),worker.getFromdate(),worker.getAccountStatus());
//	  obj.setRole(worker.getUser().getRoles().);
	  User user=worker.getUser();
	  ERole role = null;
		Set<Role> roles=user.getRoles();
		for(Role rol:roles) {
			role=rol.getName();
			System.out.println(rol.getName());
			break;
			
		}
		obj.setRole(role.toString());
	    workersfi.add(obj);
   }

		return workersfi;
}

public workersDetails getEmployeeById(Integer workerId) {
	
	
	workersDetails det=workerrepo.getById(workerId);
	workersDetails res=new workersDetails(det.getWorkerCode(),det.getMobileNo(),det.getFullName(),det.getDoorNo(),
			det.getArea(),det.getSalary(),det.getAuthor(),det.getFromdate(),det.getAccountStatus(),
			new User(det.getUser().getId(),det.getUser().getUsername(),det.getUser().getEmail(),
					det.getUser().getRoles()));
	
	
//	res.setUser().setEmail(det.getUser().getEmail());
//	res.getUser().setUsername(det.getUser().getUsername());
	
	
	return res;
}

public List<Tracking> getEmployeeActivity(Integer rangeFrom, Integer rangeTo) {
	
	return trackingRepo.getDataFromTracking(rangeFrom,rangeTo);
}

public MessageResponse updateProfile(Integer workerId, UpdateRequest request) {
	
	
	workersDetails det=workerrepo.getById(workerId);	
//	System.out.println(request.getPassword()+"PA");
//	System.out.println(encoder.encode(request.getPassword()));
//	System.out.println(det.getUser().getPassword());
	if(encoder.matches(request.getPassword(), det.getUser().getPassword())) {
		det.setFullName(request.getFullName());
		det.setMobileNo(request.getMobileNo());
		det.getUser().setEmail(request.getEmail());
		det.getUser().setUsername(request.getUsername());
		workerrepo.save(det);
		tracking.trackActivity("profile changed", workerId, det.getUser().getUsername());
		return new MessageResponse("Successfully Updated");
	}else {
		
		return new MessageResponse("Wrong Password");
	}
	
	
}

 public MessageResponse changePassword(Integer workerId, String password, String oldpass) {
	 
	 workersDetails det=workerrepo.getById(workerId);
	 
		if(encoder.matches(password, det.getUser().getPassword())) {
          	return new MessageResponse("You Entered Old Password...");
		}else {
			String a="";
			if(det.getAccountStatus().equalsIgnoreCase("YETTOACTIVATE")) {
				det.setAccountStatus("ACTIVE");
				a="New Account Activated";
			}
			det.getUser().setPassword(encoder.encode(password));
			workerrepo.save(det);
			tracking.trackActivity("password changed"+"a", workerId, det.getUser().getUsername());
			return new MessageResponse("Password Updated");
		}
 }

public MessageResponse deleteEployee(Integer code, String name) {
	 workersDetails det=workerrepo.getById(code);
	   workerrepo.delete(det);
	return null;
}

public MessageResponse roleChange(Integer workerId, String role, String name) {
	 workersDetails det=workerrepo.getById(workerId);

	 Set<Role> roleObj=getRoleFromDp(role);
	 det.getUser().setRoles(roleObj);
	 workerrepo.save(det);
	 tracking.trackActivity("Role Changed To"+role+" By Owner", workerId , name);
	return new MessageResponse("ROLE CHANGED TO"+" "+role);
}

public  List<Tracking> getEmployeeActivityAll(Integer rangeFrom, Integer rangeTo, Integer code) {
	// TODO Auto-generated method stub
	return trackingRepo.getDataFromTracking(rangeFrom,rangeTo,code);
}

public String addRole(Role ro) {
	
	roleRepository.save(ro);
	return "sucess";
}
	
	
	

}
