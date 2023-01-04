package com.love.logic.payload.response;

import java.util.List;

import lombok.Data;

@Data
public class TodayMovies {

	
	private String msg;
	public TodayMovies(String msg, boolean error, List<MovieListHome> obj) {
		super();
		this.msg = msg;
		this.error = error;
		this.obj = obj;
	}
	private boolean error;
	private List<MovieListHome> obj;
	
	
}
