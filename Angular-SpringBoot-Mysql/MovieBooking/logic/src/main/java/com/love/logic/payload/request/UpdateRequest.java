package com.love.logic.payload.request;

import lombok.Data;

@Data
public class UpdateRequest {
	
	private String email;
	private String username;
	private String fullName;
	private String mobileNo;
	private String password;


}
