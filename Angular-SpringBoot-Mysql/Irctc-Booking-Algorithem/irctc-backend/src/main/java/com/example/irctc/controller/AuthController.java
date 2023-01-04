package com.example.irctc.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.irctc.dto.request.AvailableRequest;
import com.example.irctc.dto.request.LoginRequest;
import com.example.irctc.dto.request.SignupRequest;
import com.example.irctc.dto.response.AvaiAbilityResponse;
import com.example.irctc.dto.response.JwtResponse;
import com.example.irctc.dto.response.MessageResponse;
import com.example.irctc.model.AccountDetails;
import com.example.irctc.model.ERole;
import com.example.irctc.model.Role;
import com.example.irctc.model.User;
import com.example.irctc.repo.AccountDetailsRepo;
import com.example.irctc.repo.RoleRepository;
import com.example.irctc.repo.UserRepository;
import com.example.irctc.security.jwt.JwtUtils;
import com.example.irctc.security.services.UserDetailsImpl;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/irctc/auth")
public class AuthController {
	
	
  @Autowired
  private  AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private  RoleRepository roleRepository;
  
  @Autowired
  private AccountDetailsRepo accountdetailRepo;

  @Autowired
  PasswordEncoder encoder;
  


  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

   // SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();  
    
    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }
	
    // Create new user's account
    User user = new User(signUpRequest.getUsername(), 
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));
    AccountDetails accountDetails=new AccountDetails(signUpRequest.getFullName(), signUpRequest.getMobileNo(), signUpRequest.getAge(), signUpRequest.getDoorNo(), signUpRequest.getStreetName(),signUpRequest.getDistrict(), signUpRequest.getArea(), signUpRequest.getState(), signUpRequest.getNation(), signUpRequest.getPincode(), signUpRequest.getAuthorno());

    user.setAccountDetails(accountDetails);

  //  Set<String> strRoles = signUpRequest.getRole();
    
    
    Set<Role> roles = new HashSet<>();

    
      Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
      user.setRoles(roles);
      userRepository.save(user);
    
//    else {
//      strRoles.forEach(role -> {
//        switch (role) {
//        case "admin":
//          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(adminRole);
//
//          break;
//        case "mod":
//          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(modRole);
//
//          break;
//        default:
//          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(userRole);
//        }
//      });
//    }

  

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
  
  @PostMapping("/validation")
  public ResponseEntity<AvaiAbilityResponse> checkAvailable(@RequestBody AvailableRequest request) {
	  if(request.getInput().equalsIgnoreCase("email")) {
		  if(userRepository.existsByEmail(request.getValue())){
			  return ResponseEntity.ok(new AvaiAbilityResponse("AVAIL"));
		  }
		  else {
			  return ResponseEntity.ok(new AvaiAbilityResponse("NOTAVAIL"));
		  }
	  }
	  if(request.getInput().equalsIgnoreCase("mobileNo")) {
		if(accountdetailRepo.existsByMobile(request.getValue())) {
			return ResponseEntity.ok(new AvaiAbilityResponse("AVAIL"));
		}
		else {
			 return ResponseEntity.ok(new AvaiAbilityResponse("NOTAVAIL"));
		}
	  }
	  if(request.getInput().equalsIgnoreCase("userName")) {
              if(userRepository.existsByUsername(request.getValue())){
			       
            	  return ResponseEntity.ok(new AvaiAbilityResponse("AVAIL"));	
            	  }
              else {
            	  return ResponseEntity.ok(new AvaiAbilityResponse("NOTAVAIL"));              }
	  }
	  if(request.getInput().equalsIgnoreCase("author")) {
		  
		    if(accountdetailRepo.existsByAuthorno(request.getValue())){
		    	return ResponseEntity.ok(new AvaiAbilityResponse("AVAIL"));		    }
		    else {
		    	 return ResponseEntity.ok(new AvaiAbilityResponse("NOTAVAIL"));		    }
 	  }
	  
	  
	  return null;
	  
  }
  
  @GetMapping("/tokenVaidate/{token}")
  public ResponseEntity<?> tokenValidate(@PathVariable String token){
	  boolean status=jwtUtils.validateJwtToken(token);
	  String msg;
	  if(status) {
		  msg="valid";
	  }
	  else {
		  msg="notvalid";
	  }
	  
	return ResponseEntity.ok(new MessageResponse(msg));
	  
  }
  
  
}
