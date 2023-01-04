package com.example.irctc.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.example.irctc.model.Passenger;

import lombok.Data;

@Data
public class BookingRequest {
	
	private String fromSta;
	private String toSta;
	private String clasaa;
	private String classType;
	private LocalDate dateOftrain;
	private String tripcode;
	 private int trainNo;
     private int noOfPassenger;
   private String paymentMode;
   private long mobileNo;
	
 private Long user_id;

	
	private List<Passenger> passengers;

}
