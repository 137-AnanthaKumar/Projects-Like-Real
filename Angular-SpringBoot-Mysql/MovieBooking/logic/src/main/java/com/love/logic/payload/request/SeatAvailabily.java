package com.love.logic.payload.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class SeatAvailabily {
	

	public SeatAvailabily(String movieId, String movieName, String description, String movieCast, int prize,
			LocalTime time, LocalDate date, boolean bookingOpened, String movieStatus) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.description = description;
		this.movieCast = movieCast;
		this.prize = prize;
		this.time = time;
		this.date = date;
		this.bookingOpened = bookingOpened;
		this.movieStatus = movieStatus;
	}
	public SeatAvailabily() {
		// TODO Auto-generated constructor stub
	}
	private String movieId;
	private String movieName;
	private String description;
	private String trainlerUrl;
	private String movieCast;
	private int screen;
	private int prize;
    private LocalTime time;
	private LocalDate date;
	private boolean bookingOpened;
	private String movieStatus;
	private List<String> bookedseats;
	private List<String> lockedseats;
	private List<String> pathseats;

}
