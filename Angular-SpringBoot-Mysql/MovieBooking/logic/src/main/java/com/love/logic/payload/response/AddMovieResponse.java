package com.love.logic.payload.response;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class AddMovieResponse {
	public AddMovieResponse(String msg, List<LocalDate> sucess, List<LocalDate> failed, List<LocalDate> chengeDate) {
		super();
		this.msg = msg;
		this.sucess = sucess;
		this.failed = failed;
		this.chengeDate = chengeDate;
	}
	String msg;
	List<LocalDate> sucess;
	List<LocalDate> failed;
	List<LocalDate> chengeDate;
	
	

}
