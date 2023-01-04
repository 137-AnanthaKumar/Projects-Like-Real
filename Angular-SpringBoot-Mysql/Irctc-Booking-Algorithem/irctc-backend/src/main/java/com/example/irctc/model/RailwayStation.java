package com.example.irctc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class RailwayStation {

	@Id
	private String stationCode;
	
	private String stationName;
	
}
