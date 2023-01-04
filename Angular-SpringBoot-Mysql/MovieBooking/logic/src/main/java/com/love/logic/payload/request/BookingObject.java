package com.love.logic.payload.request;

import java.util.List;

import lombok.Data;

@Data
public class BookingObject {
	
	private String movieId;
	private String emailId;
	private long mobile;
	private List<String> selectedSeats;

}
