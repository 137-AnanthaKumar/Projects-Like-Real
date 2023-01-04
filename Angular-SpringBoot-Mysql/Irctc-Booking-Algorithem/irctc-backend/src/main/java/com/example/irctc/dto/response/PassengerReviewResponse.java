package com.example.irctc.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class PassengerReviewResponse {
	
	public PassengerReviewResponse(String trainName, int trainNo, String fromSta, String toSta, String endOfJourney,
			String dateOfJourney, String startTime, String endTime, String coachType, String classType) {
		super();
		this.trainName = trainName;
		this.trainNo = trainNo;
		this.fromSta = fromSta;
		this.toSta = toSta;
		this.endOfJourney = endOfJourney;
		this.dateOfJourney = dateOfJourney;
		this.startTime = startTime;
		this.endTime = endTime;
		this.coachType = coachType;
		this.classType = classType;
	}
	public PassengerReviewResponse() {
		// TODO Auto-generated constructor stub
	}
	private String trainName;
	private int trainNo;
	private String fromSta;
	private String toSta;
    private String  endOfJourney;
	  
    private String dateOfJourney;
	private String startTime;
    private String endTime;
    private String availSta;
    private String coachType;
    private String classType;
	

}
