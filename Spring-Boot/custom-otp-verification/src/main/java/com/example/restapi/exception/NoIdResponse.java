package com.example.restapi.exception;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoIdResponse extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public NoIdResponse(String message) {
		super(message);
		
	}

}
