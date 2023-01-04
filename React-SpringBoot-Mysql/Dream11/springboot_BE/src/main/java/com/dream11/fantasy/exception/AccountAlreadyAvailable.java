package com.dream11.fantasy.exception;

public class AccountAlreadyAvailable extends RuntimeException{
	private static final long serialVersionUID = 1L;

	
	public AccountAlreadyAvailable(String messege) {
		
		super(messege);
	}
}
