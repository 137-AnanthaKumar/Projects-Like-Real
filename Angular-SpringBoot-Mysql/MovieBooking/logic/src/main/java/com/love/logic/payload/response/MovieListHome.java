package com.love.logic.payload.response;


import java.sql.Time;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

import lombok.Data;

@Data
public class MovieListHome {
	
	private String movieId;
	private String movieName;
	//private List<Time> times;
	private List<String> times;
	public MovieListHome(String movieId, String movieName, List<String> times) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.times = times;
	}
	public MovieListHome() {
		// TODO Auto-generated constructor stub
	}

}
