package com.collabera.photographer_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class AdminUsersModel {
	
	private int id;
	private String name;
	private long contact;
	private String gmail;
	private String role;
	private String userName;
	private String category;

}
