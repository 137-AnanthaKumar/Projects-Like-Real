package com.example.irctc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table
public class Train {
	
	@Id
	private int trainNo;
	
	private String trainName;
	private int totalCoach;
	private int totalFirstclassAcCouch;
	private int totalFirstclassAcCouchSeats;
	private int totalSecondClassAc;
	private int totalSecondClassAcSeats;
    private int totalThirdClassAc;
	private int totalThirdClassAcSeats;
	private int totalSeeperClass;
	private int totalSleeperclassSeat;
	
//	@OneToMany(cascade = CascadeType.ALL)
//	 @JoinColumn( name = "train_id", referencedColumnName = "trainNo")
//	private List<Trip> trips=new ArrayList<Trip>();
//	
//	
//	public void addTrips(Trip trip) {
//		this.trips.add(trip);
//	}

	


}
