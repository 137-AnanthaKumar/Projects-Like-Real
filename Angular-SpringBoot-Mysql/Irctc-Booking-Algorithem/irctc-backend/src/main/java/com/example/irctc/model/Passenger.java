package com.example.irctc.model;


import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table
public class Passenger {

	@Id
	private String passegerId;
	private String passengerName;
	private int passengerAge;
	private String gender;
	private String ticketStatus;
	private String seatNo="KNOW";
	
	@OneToOne
	@JoinColumn(name="slot_fk")
	private Slots slot;
	
	
	
	
	

	
	
}
