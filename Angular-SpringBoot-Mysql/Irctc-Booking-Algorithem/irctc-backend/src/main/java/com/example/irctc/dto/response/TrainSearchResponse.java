package com.example.irctc.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainSearchResponse {
	
	private String tripId;
	private String fromStation;
	private String toStaion;
    private LocalDate endOfJourney;
    private String claass;
    private String classType;
  
    private LocalDate dateOfJourney;
    private LocalTime startTime;
	private LocalTime endTime;
	private int trainno;
	private String trainName;
	private List<TicketAvailResponse> avaiability;
	private HashSet<String> classes;
	public TrainSearchResponse(String fromStation, String toStaion, LocalDate endOfJourney, LocalDate dateOfJourney,
			LocalTime startTime, LocalTime endTime, int trainno, String trainName,String tripId) {
		super();
		this.fromStation = fromStation;
		this.toStaion = toStaion;
		this.endOfJourney = endOfJourney;
		this.dateOfJourney = dateOfJourney;
		this.startTime = startTime;
		this.endTime = endTime;
		this.trainno = trainno;
		this.trainName = trainName;
		this.tripId = tripId;
	} 
}
