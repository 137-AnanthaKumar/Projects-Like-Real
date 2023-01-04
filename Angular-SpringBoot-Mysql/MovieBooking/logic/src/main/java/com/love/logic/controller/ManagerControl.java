package com.love.logic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.love.logic.models.User;
import com.love.logic.models.workersDetails;
import com.love.logic.payload.request.UpdateRequest;
import com.love.logic.payload.response.MessageResponse;
import com.love.logic.security.jwt.AuthTokenFilter;
import com.love.logic.security.jwt.JwtUtils;
import com.love.logic.service.ManagerServices;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/petproject/manager/control")
public class ManagerControl {
	
	@Autowired
	JwtUtils util;
	
	@Autowired 
	AuthTokenFilter authfilter;
	
	@Autowired 
	ManagerServices service; 
	@PostMapping("/addemployee/{role}")
//	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER')")
	public ResponseEntity<?> addAdminByManager(@RequestBody workersDetails worker,@PathVariable String role,@RequestHeader(value="authorization",defaultValue = "") String auth){
		String name=util.getUserNameFromJwtToken(auth.substring(7, auth.length()));
		return  ResponseEntity.ok(service.createNewWorker(worker,name,role));
	}
	
	@DeleteMapping("/deleteEmployee/{code}")
	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER')")
	public ResponseEntity<?> deleteEmployee(@PathVariable Integer code,@RequestHeader(value="authorization",defaultValue = "") String auth){
		String name=util.getUserNameFromJwtToken(auth.substring(7, auth.length()));
		return  ResponseEntity.ok(service.deleteEployee(code,name));
	}
	
	
	
	
	
	
	@PutMapping("/blockAdmin/{workerCode}")
	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER')")
	public ResponseEntity<?> blockAdminByManager(@PathVariable Integer workerCode,@RequestHeader(value="authorization",defaultValue = "") String auth){
		
		String name=util.getUserNameFromJwtToken(auth.substring(7, auth.length()));
		
		return  ResponseEntity.ok(service.blockAdmin(workerCode,name));
		
	}
	
	@PutMapping("/ActivateAdmin/{workerCode}")
	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER')")
	public ResponseEntity<?> ActivateAdminByManager(@PathVariable Integer workerCode,@RequestHeader(value="authorization",defaultValue = "") String auth){
		
		String name=util.getUserNameFromJwtToken(auth.substring(7, auth.length()));
//		System.out.println(name);
		return  ResponseEntity.ok(service.unblockAdmin(workerCode,name));
		
	}
	
	@PostMapping("/makeManager")
	@PreAuthorize("hasRole('OWNER')")
	public ResponseEntity<?> addManagerByOwener(@RequestBody workersDetails worker,@RequestHeader(value="authorization",defaultValue = "") String auth){
		String name=util.getUserNameFromJwtToken(auth.substring(7, auth.length()));
		return  ResponseEntity.ok(service.createNewWorker(worker,name,"manager"));
	
	}
	
	
	@GetMapping("/getRole/workers")
	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER') or hasRole('ADMIN')")
	public ResponseEntity<?> getRole(@RequestHeader(value="authorization",defaultValue = "") String auth){
		String name=util.getUserNameFromJwtToken(auth.substring(7, auth.length()));
    	return  ResponseEntity.ok(service.getRole(name));
	
	}
	
	@GetMapping("/getall/workers")
	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER') or hasRole('ADMIN')")
	public ResponseEntity<?> getAllEmployee() {
	
		
    	return  ResponseEntity.ok(service.getAllEmployee());
	
	}
	
	@GetMapping("/getOneWorker/workers/{workerId}")
	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER') or hasRole('ADMIN')")
	public ResponseEntity<?> getEmployee(@PathVariable Integer workerId) {
	
    	return  ResponseEntity.ok(service.getEmployeeById(workerId));
	
	}
	
	
	@GetMapping("/getAllActivity/workers/{rangeFrom}/{rangeTo}")
	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER')")
	public ResponseEntity<?> getEmployeeActivitiy(@PathVariable Integer rangeFrom, @PathVariable Integer rangeTo) {
	
    	return  ResponseEntity.ok(service.getEmployeeActivity(rangeFrom,rangeTo));
	
	}
	
	@GetMapping("/getAllActivity/workers/{rangeFrom}/{rangeTo}/{code}")
	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER') or hasRole('ADMIN')")
	public ResponseEntity<?> getEmployeeActivitiy(@PathVariable Integer rangeFrom, @PathVariable Integer rangeTo,@PathVariable Integer code) {
	
    	return  ResponseEntity.ok(service.getEmployeeActivityAll(rangeFrom,rangeTo,code));
	
	}
	@PutMapping("/update/workers/{workerId}")
	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER') or hasRole('ADMIN')")
	public ResponseEntity<?> updateMyProfile(@PathVariable Integer workerId,@RequestBody UpdateRequest request) {
	
    	return  ResponseEntity.ok(service.updateProfile(workerId,request));
	
	}
	
	@PutMapping("/updatePassword/workers/{workerId}")
	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER') or hasRole('ADMIN')")
	public ResponseEntity<?> chengePassword(@PathVariable Integer workerId,@RequestBody UpdateRequest request) {
	//getFullName is old password
    	return  ResponseEntity.ok(service.changePassword(workerId,request.getPassword(),request.getFullName()));
	
	}
	
	
	@PutMapping("/roleChange/workers/{workerId}/{role}")
	@PreAuthorize("hasRole('OWNER')")
	public ResponseEntity<?> roleChange(@PathVariable Integer workerId,@PathVariable String role,@RequestHeader(value="authorization",defaultValue = "") String auth) {
	
		
		String name=util.getUserNameFromJwtToken(auth.substring(7, auth.length()));
    	return  ResponseEntity.ok(service.roleChange(workerId,role,name));
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
