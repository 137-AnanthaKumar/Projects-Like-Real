package com.love.logic.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name="booking")
@Data
public class Bookings {
	
	@Id
	private String ticketId;
	private int noOdMembers;
	private LocalTime bookingTime;
	private LocalDate bookingDate;
	
    private long mobileNo;
    private String mailId;
	
	
    @OneToOne(cascade=CascadeType.ALL,orphanRemoval = true)
    private User user;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    @OneToOne(cascade=CascadeType.ALL,orphanRemoval = true)
    private MovieTime movieDetails;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name="ticketId")
	private List<Seat> seatsForThisBooking=new ArrayList<Seat>();
	
	
	
	}
