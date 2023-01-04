package com.example.irctc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class DistanceBetweenStation {
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	private String station1;
	private String station2;
	private float distance;
	
	
	public DistanceBetweenStation() {
		
	}

	public DistanceBetweenStation(String station1, String station2, float distance) {
		super();
		this.station1 = station1;
		this.station2 = station2;
		this.distance = distance;
	}
	
	

}
