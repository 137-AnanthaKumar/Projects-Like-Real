package com.collabera.photographer_app.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class AdminAndUsers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private long contact;
	private String gmail;
	private String role;
	private String category;
	private String userName;
	private String password;
}
