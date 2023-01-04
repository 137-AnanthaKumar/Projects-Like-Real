package com.example.irctc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class RouteStations {
	@Id
	private String stationCode;
	private int lineNo;
	private int distanseFromStart;
	
	@OneToOne
	@JoinColumn(name="station_fk")
	private RailwayStation station;
   
	   
	
}
