package com.example.irctc.model;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@Data
@Entity
public class AccountDetails {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	private String fullName;
	private String mobile;
	private String mobileVerified="UNVERIFIED";
	private int age;
	private String doorNo;
	private String streetName;
	private String location;
	private String area;
	private String state;
	private String nation;
    private int pincode;
	private String authorno;
	
	public AccountDetails() {
		
	}
	public AccountDetails(String fullName, String mobile, int age, String doorNo, String streetName, String location,
			String area, String state, String nation, int pincode, String authorno) {
		super();
		System.out.println(fullName);

		this.fullName = fullName;
		this.mobile = mobile;
		this.age = age;
		this.doorNo = doorNo;
		this.streetName = streetName;
		this.location = location;
		this.area = area;
		this.state = state;
		this.nation = nation;
		this.pincode = pincode;
		this.authorno = authorno;
	}
	private String authorVerified="UNVERIFIED";
	
//	@OneToOne(mappedBy = "accountDetails",cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonIgnoreProperties
//	private User user;
	
	
	  
	
	

}
