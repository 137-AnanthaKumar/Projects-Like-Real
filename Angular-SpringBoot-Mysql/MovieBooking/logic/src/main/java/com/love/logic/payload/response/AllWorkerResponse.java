package com.love.logic.payload.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AllWorkerResponse {
private int workerCode;
	
	private String fullName;
	private String doorNo;
	private String area;
	private int salary;
	private String mobileNo;
   private String role;

	private LocalDate fromdate;
	private String accountStatus;
	public AllWorkerResponse(int workerCode, String fullName, String doorNo, String area,String mobileNo,
			LocalDate fromdate, String accountStatus) {
		super();
		this.workerCode = workerCode;
		this.fullName = fullName;
		this.mobileNo= mobileNo;
		this.doorNo = doorNo;
		this.area = area;
	
		this.fromdate = fromdate;
		this.accountStatus = accountStatus;
	}

}
