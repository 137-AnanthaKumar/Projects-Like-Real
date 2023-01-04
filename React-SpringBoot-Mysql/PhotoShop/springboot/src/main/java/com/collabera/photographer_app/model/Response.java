package com.collabera.photographer_app.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.collabera.photographer_app.dto.AdminAndUsers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
	
	String msg;
	
	String jwt;
	
	Collection<? extends GrantedAuthority> role;
	
	AdminUsersModel usersModel;
	String userId;
	public Response(String msg, String jwt, Collection<? extends GrantedAuthority> role, AdminUsersModel usersModel) {
		super();
		this.msg = msg;
		this.jwt = jwt;
		this.role = role;
		this.usersModel = usersModel;
	}
	public Response(String msg, String jwt, Collection<? extends GrantedAuthority> role, String userId) {
		super();
		this.msg = msg;
		this.jwt = jwt;
		this.role = role;
		this.userId = userId;
	}


}
