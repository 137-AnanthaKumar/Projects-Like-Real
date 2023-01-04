package com.love.logic.payload.response;

import lombok.Data;

@Data
public class MessageResponse {
  public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

private String message;
  private int a;
  private String status;
  

  public MessageResponse(String message, int a, String status) {
	super();
	this.message = message;
	this.a = a;
	this.status = status;
}

public MessageResponse(String message) {
    this.message = message;
  }

  public MessageResponse(String message, int a) {
	  this.message = message;
	  this.a = a;
	  
  }

public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}