package com.dream11.fantasy.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream11.fantasy.SpringSecurity.UserDetailsImpl;
import com.dream11.fantasy.jwt.JwtUtils;
import com.dream11.fantasy.model.ERole;
import com.dream11.fantasy.model.Role;
import com.dream11.fantasy.model.UserAccount;
import com.dream11.fantasy.model.UserEntity;
import com.dream11.fantasy.payloadRequest.LoginRequest;
import com.dream11.fantasy.payloadRequest.SignupRequest;
import com.dream11.fantasy.payloadResponse.JwtResponse;
import com.dream11.fantasy.payloadResponse.MessageResponse;
import com.dream11.fantasy.repository.RoleRepository;
import com.dream11.fantasy.repository.UserAccountRepo;
import com.dream11.fantasy.repository.UserRepository;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;
  
  @Autowired
  UserAccountRepo userActRepo;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
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
  public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.status(HttpStatus.ACCEPTED)
          
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.status(HttpStatus.ACCEPTED)
          
          .body(new MessageResponse("Error: Email is already in use!"));
    }
    if(userActRepo.existsByAccountName(signUpRequest.getAccount().getAccountName())) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessageResponse("Error: Account Name is already in use!"));

    }

    // Create new user's account
//    UserEntity user = new UserEntity(signUpRequest.getUsername(),
//               signUpRequest.getEmail(),
//               encoder.encode(signUpRequest.getPassword()),signUpRequest.getAccount());
    

     Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();
    
    UserAccount userAcount=new UserAccount();
    userAcount.setAccountName(signUpRequest.getAccount().getAccountName());
    UserEntity userEnt=new UserEntity();
    userEnt.setPassword(encoder.encode(signUpRequest.getPassword()));
    userEnt.setUsername(signUpRequest.getUsername());
    userEnt.setEmail(signUpRequest.getEmail());
   
    

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER);
       roles.add(userRole);
    } 
    userEnt.setRoles(roles);
    userAcount.setUser(userEnt);
    userActRepo.save(userAcount);
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
