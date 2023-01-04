package com.example.irctc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table
public class Slots {
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long slotId;
	   private int seatNo;
	   private String coachNo;
	   private int coachposition;
	   private String coachType;
	   private String slotStatus="AVAIL";
	   
	   private String passId;
	   
	   @ManyToOne
	   @JoinColumn(name="trip_id")
	   private Trip trip; 

}
