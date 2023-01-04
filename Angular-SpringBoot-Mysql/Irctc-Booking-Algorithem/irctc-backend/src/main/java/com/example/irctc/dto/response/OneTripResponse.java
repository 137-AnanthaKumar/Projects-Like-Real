package com.example.irctc.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class OneTripResponse {
	private String fromStation;
	private String toStaion;
    private LocalDate endOfJourney;
  
    private LocalDate dateOfJourney;
    private LocalTime startTime;
	private LocalTime endTime;
	private int trainno;
	private String trainName;
	private String availa;
	private String classs;
}
