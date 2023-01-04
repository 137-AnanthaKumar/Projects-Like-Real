package com.love.logic.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="seat")
@Data
public class Seat {
	
	@Id
	private String seatId;
	private String seatLine; //klike A B C D.....
	private String seatNo;
	private String movieId;
	//A1, A2...
	private String status; //Locked , booked 
	

}
