package com.example.irctc.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExcepTionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(TrainNameAndNoAvailable.class)
	public ResponseEntity<ErrorResponse> commenResponse(TrainNameAndNoAvailable available){
		ErrorResponse response=new ErrorResponse();
		response.setMessege(available.getMessage());
		response.setTime(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.FOUND).body(response);
		
	}

}
