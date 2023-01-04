package com.example.irctc.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table
public class Route {
	
	

	@Id
	private String routeId;
	

	private String startingStation;
	private String endStation;
	private int distance;
	private int noOfStations;
	
	public Route() {
		
	}
	
	public Route(String routeId, String startingStation, String endStation,  int distance,int noOfStations) {
		super();
		this.routeId = routeId;
		this.startingStation = startingStation;
		this.endStation = endStation;
		this.distance = distance;
		this.noOfStations = noOfStations;
	}
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name="route_fk")
   private List<RouteStations> stations=new ArrayList<RouteStations>();
	
	//@OneToOne(mappedBy = "accountDetails",cascade = CascadeType.ALL, orphanRemoval = true)
	
//	@ManyToMany(fetch = FetchType.LAZY)
//	  @JoinTable(  name = "rout_station", 
//	        joinColumns = @JoinColumn(name = "routeId"), 
//	        inverseJoinColumns = @JoinColumn(name = "stationCode"))
//	  private Set<RailwayStation> stations = new HashSet<>();

	
	
}
