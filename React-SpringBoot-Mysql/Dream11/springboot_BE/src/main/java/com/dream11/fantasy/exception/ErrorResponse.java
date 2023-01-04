package com.dream11.fantasy.exception;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class ErrorResponse {
	
	private String messege;
	private String error;
	private LocalDateTime time;

}
