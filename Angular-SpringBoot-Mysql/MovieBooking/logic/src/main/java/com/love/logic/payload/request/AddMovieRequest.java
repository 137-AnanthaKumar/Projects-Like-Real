package com.love.logic.payload.request;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AddMovieRequest {
	private String movieName;
	private String description;
	private String movieCast;
	private int prize;
	private int availableSeats;
	private String trainlarUrl;
	
	private LocalTime addedTime;
	private String time;
	private boolean bookingOpened;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	private String movieStatus;

	public boolean getBookingOpened() {
		
		// TODO Auto-generated method stub
		return bookingOpened;
	}
	
}
