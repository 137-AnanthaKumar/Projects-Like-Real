package com.dream11.fantasy.exception;

import lombok.Data;

@Data
public class ContastJoiningFailing  extends RuntimeException{
	
	private String error;
	public ContastJoiningFailing(String messege, String error) {
		super(messege);
		this.error=error;
		
	}
	
	
	
	
	

}
