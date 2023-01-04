package com.love.logic.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="workers")
@Data
public class workersDetails {
	
	public workersDetails(int workerCode, String mobileNo, String fullName, String doorNo, String area, int salary,
			String author, LocalDate fromdate, String accountStatus, User user) {
		super();
		this.workerCode = workerCode;
		this.mobileNo = mobileNo;
		this.fullName = fullName;
		this.doorNo = doorNo;
		this.area = area;
		this.salary = salary;
		this.author = author;
		this.fromdate = fromdate;
		this.accountStatus = accountStatus;
		this.user = user;
	}
	public workersDetails(int workerCode, String fullName, String doorNo, String area,
			LocalDate fromdate, String accountStatus) {
		super();
		this.workerCode = workerCode;
		this.fullName = fullName;
		this.doorNo = doorNo;
		this.area = area;
	
		this.fromdate = fromdate;
		this.accountStatus = accountStatus;
	}
	public workersDetails() {
		
	}

	public workersDetails(int workerCode, String fullName, String doorNo, String area, int salary, String author,
			String firstPass, LocalDate fromdate, String accountStatus) {
		super();
		this.workerCode = workerCode;
		this.fullName = fullName;
		this.doorNo = doorNo;
		this.area = area;
		this.salary = salary;
		this.author = author;
		this.firstPass = firstPass;
		this.fromdate = fromdate;
		this.accountStatus = accountStatus;
	}


	@Id
	private int workerCode;
	private String mobileNo;
	private String fullName;
	private String doorNo;
	private String area;
	private int salary;
	private String author;
	private String firstPass;
	private LocalDate fromdate;
	private String accountStatus="YETTOACTIVATE";
	
	
	@OneToOne
	private User user;
	


}
