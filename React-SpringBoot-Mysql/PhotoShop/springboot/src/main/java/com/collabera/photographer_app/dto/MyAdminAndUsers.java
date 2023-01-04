package com.collabera.photographer_app.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyAdminAndUsers implements UserDetails{
	
	@Autowired
	private AdminAndUsers adminAndUsers;





	public MyAdminAndUsers(AdminAndUsers adminAndUsers) {
		super();
		this.adminAndUsers = adminAndUsers;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(adminAndUsers.getRole());
		List<SimpleGrantedAuthority> authorities=new ArrayList<SimpleGrantedAuthority>();
		authorities.add(simpleGrantedAuthority);	
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return  adminAndUsers.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return adminAndUsers.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
