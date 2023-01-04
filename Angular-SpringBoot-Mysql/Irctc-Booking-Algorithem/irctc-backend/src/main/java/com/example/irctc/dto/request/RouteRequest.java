package com.example.irctc.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class RouteRequest {
	
	
	private String routeId;
	private String startingStation;
	private String endStation;
	private int distance;
	private int noOfStations;
	private List<String> routeStation;

}
