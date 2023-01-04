package com.example.irctc.exception;

import lombok.Data;


public class TrainNameAndNoAvailable extends RuntimeException{

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	public TrainNameAndNoAvailable(String messege) {
		super(messege);
	}
	

}
