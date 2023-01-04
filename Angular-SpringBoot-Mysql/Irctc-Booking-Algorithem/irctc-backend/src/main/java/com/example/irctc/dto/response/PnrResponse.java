package com.example.irctc.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PnrResponse {
	
	private boolean msg;
	private long pnr;
	private int trainNo;
	private String fromSta;
	private String toSta;
	private LocalDate  dateOfbooking;

	private LocalDate dateOfJourney;
	private LocalTime time;
	private List<PnrPassRespo> passengers=new ArrayList<PnrPassRespo>();
	

}
