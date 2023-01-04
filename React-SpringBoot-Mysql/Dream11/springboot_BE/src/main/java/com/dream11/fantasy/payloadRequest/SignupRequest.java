package com.dream11.fantasy.payloadRequest;

import java.util.Set;

import javax.validation.constraints.*;

import com.dream11.fantasy.model.UserAccount;

public class SignupRequest {
  
  private String username;

  private String email;
  
  
  private UserAccount account;
  
 

private Set<String> role;

  
  private String password;

  public String getUsername() {
	  System.out.println(username);
    return username;
   
  }

  public void setUsername(String username) {
	  System.out.println(username);
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }

  public UserAccount getAccount() {
	  System.out.println(account.getAccountName());
		return account;
	}

public void setAccount(UserAccount account) {
	System.out.println(account.getAccountName());
	this.account = account;
}
}
