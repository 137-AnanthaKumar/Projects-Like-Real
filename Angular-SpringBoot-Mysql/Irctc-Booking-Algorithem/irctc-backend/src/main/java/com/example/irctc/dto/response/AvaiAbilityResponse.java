package com.example.irctc.dto.response;

import lombok.Data;

@Data
public class AvaiAbilityResponse {
	
	private String msg;

	public AvaiAbilityResponse(String msg) {
		super();
		this.msg = msg;
	}


}
