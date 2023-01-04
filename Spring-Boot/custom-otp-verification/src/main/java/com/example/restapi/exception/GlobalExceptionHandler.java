package com.example.restapi.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {
    
	
	@ExceptionHandler(NoIdResponse.class)
	public ResponseEntity<ErrorResponse> NoIdFoundException(NoIdResponse exe){
		
		ErrorResponse response=new ErrorResponse();
		response.setMessage(exe.getMessage());
		response.setLocalDateTime(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		
	}
	
	@ExceptionHandler(ReSourseNotFound.class)
	public ResponseEntity<ErrorResponse> ResourceNotFoundException(){
		
		ErrorResponse response=new ErrorResponse();
		response.setMessage("No ReSourceS found");
		response.setLocalDateTime(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		
	}
}

