package com.example.irctc.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@Data
@Entity
@Table
public class Ticket {
	
	@Id
	private long pnr;
	
    private LocalDate bookedDate;
	
	private LocalTime bookedTime;
	
	private int noOfPassenger;
	private String clasaa;
	private String classType;
	private String paymentMode;
	private long mobileNo;
	
	
	@ManyToOne
	@JoinColumn(name="userId")
    @JsonIgnoreProperties(value = {"ticket", "hibernateLazyInitializer"})
    private User user;
	
	@ManyToOne
	@JoinColumn(name="trip_fk")
	private Trip trip;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="pnrId")
	private List<Passenger> passengers=new ArrayList<Passenger>();
	
	public Ticket() {
		
	}
	

	public void addPlayer(Passenger passeng) {
		this.passengers.add(passeng);
	}
	
	
	
}
