package com.example.irctc.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table
public class TripScheduler {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long scheduleId;
	
	private int trainNo;
	private String fromStation;
    private String toStaion;
    
    private String startingTime;
    private String endTiming;
    
    
    
	
	private int sleeperPrize;
	private int totalSLseats;
	private int tatkalForSl;


	
	
	private int firstClassAcPrize;
	private int totalFirstAcseats;
	private int tatkalForFirstAc;

	
	private int secondclassAcPrize;
	private int totalSecondAcseats;
	private int tatkalForSecondAc;


	private int thirdClassAcPrize;
	private int totalThirdAcseats;
	private int tatkalForThirdAc;

	
	
	 //Schedule Details
	private int dayForCreatingTrip;
	private Date scheduleStartingDate;
	private Date scheduleEndingDate;
	private String typeOfTrip;           //DAILYTRIP, WEAKLYTRIP, MONTHLYTRIP
	private String status="ACTIVE";      //ACTIVE , STOPED, FINISHED
	
	
	
	
}
