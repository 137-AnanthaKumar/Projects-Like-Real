package com.example.irctc.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeId;

import lombok.Data;

@Data
@Entity
@Table
public class Trip {
	@Id
	@Column
	private String tripcode;
	
	@Column
	private int trainNo;
	
	@Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
     private LocalDate dateOfJourney;
	@Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endOfJourney;

	private LocalTime startTime;
	private LocalTime endTime;
	private String fromStation;
	private String toStaion;
	
	private int sleeperPrize;
	private int totalSLseats;
	private String availableSLSeats;
	private int tatkalForSl;
	private String availTatkalSLseats;

	
	
	private int firstClassAcPrize;
	private int totalFirstAcseats;
	private String availableFirstAcseats;
	private int tatkal1Acseats;
	private String availTatkal1ACseats;

	
	private int secondclassAcPrize;
	private int totalSecondAcseats;
	private String availableSecondAcseats;
    private int tatkalForSecondAc;
	private String availtatkal2ACseats;


	private int thirdClassAcPrize;
	private int totalThirdAcseats;
	private String availAbleThiredAcSeats;
	private int tatkalForThirdAc;
	private String availtatkal3ACseats;

	
	private int waitList=0;
	
	private String tatKalStatus="NOTOPEN";
	private String tripStatus="TRIPOPENED";
	
	@OneToMany
	@JoinColumn(name="trip_fk")
	private List<Ticket> ticket=new ArrayList<>();

	
//	@OneToMany(cascade = CascadeType.ALL)
//  @JoinColumn( name = "tripId")
//  private List<Slots> slots=new ArrayList<Slots>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="train_number")
	private Train train;

	
	@OneToOne(cascade=CascadeType.ALL)
     private Route route;

	public void addTicket(Ticket tic) {
	
		this.ticket.add(tic);
	}
	
	
	



}
