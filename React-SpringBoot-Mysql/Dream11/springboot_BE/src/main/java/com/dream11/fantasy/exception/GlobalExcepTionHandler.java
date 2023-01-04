package com.dream11.fantasy.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class GlobalExcepTionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(AccountAlreadyAvailable.class)
	public ResponseEntity<ErrorResponse> response(AccountAlreadyAvailable avail){
		ErrorResponse response=new ErrorResponse();
		response.setMessege(avail.getMessage());
		response.setTime(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(ContastJoiningFailing.class)
	public ResponseEntity<ErrorResponse> cotestfailResponse(ContastJoiningFailing avail){
		ErrorResponse response=new ErrorResponse();
		response.setError(avail.getError());
		response.setMessege(avail.getMessage());
		response.setTime(LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

}
