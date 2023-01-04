package com.example.irctc.dto.response;

import com.example.irctc.model.RailwayStation;

import lombok.Data;

@Data
public class Response {
	
	private String status;
	private RailwayStation station;

}
