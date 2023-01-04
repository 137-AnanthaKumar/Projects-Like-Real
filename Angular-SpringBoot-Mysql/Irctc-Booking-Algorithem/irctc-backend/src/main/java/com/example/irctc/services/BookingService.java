package com.example.irctc.services;


import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.irctc.dto.response.TicketAvailResponse;

import com.example.irctc.model.Trip;
import com.example.irctc.repo.TripRepo;

@Service
public class BookingService {

	
	@Autowired 
	private TripRepo tripRepo;
	
	public List<Trip> getTrains(String fromStation, String toStation, LocalDate date) {
		
		  List<Trip> gdgd=tripRepo.getAllOpenedTrip(fromStation,toStation,date);
		  for(Trip t:gdgd) {
			  
			  System.out.println("AVAISL");
			  System.out.println(t.getAvailableSLSeats());
		  }
			  

		return gdgd;
	}

	public TicketAvailResponse getAvailAbility(int trainNo, String fromStation, String toStation, LocalDate date, String classs) {
		    switch (classs) {
		      case "SL":
		    	  String avail=tripRepo.getAvailable(trainNo,fromStation,toStation,date);
             	  return formaterAvail(date, classs, avail);
		     case "3A":
			     String avail_3AC=tripRepo.getAvailable_3AC(trainNo,fromStation,toStation,date);
	    	     return formaterAvail(date, classs, avail_3AC);
		      case "2A":
		    	  String avail_2AC=tripRepo.getAvailable_2AC(trainNo,fromStation,toStation,date);
		    	  return formaterAvail(date, classs, avail_2AC);
		      case "1A":
		    	  String avail_1AC=tripRepo.getAvailable_2AC(trainNo,fromStation,toStation,date);
		    	  return formaterAvail(date, classs, avail_1AC);
		      case "TSL":
		    	  String avail_TSL=tripRepo.getAvailable_TSL(trainNo,fromStation,toStation,date);
		    	  return formaterAvail(date, classs, avail_TSL);
		    	  
		      case "T3A":
		    	  String avail_T3AC=tripRepo.getAvailable_T3AC(trainNo,fromStation,toStation,date);
		    	  return formaterAvail(date, classs, avail_T3AC);
		      case "T2A":
		    	  String avail_T2AC=tripRepo.getAvailable_T2AC(trainNo,fromStation,toStation,date);
		    	  return formaterAvail(date, classs, avail_T2AC);
		      case "T1A":
		    	  String avail_T1AC=tripRepo.getAvailable_T1AC(trainNo,fromStation,toStation,date);
		    	  return formaterAvail(date, classs, avail_T1AC);
			    
		    
		    }
		
		
		return null;
		
	}
	
	public TicketAvailResponse formaterAvail(LocalDate date, String classs,String avail_seats) {
		TicketAvailResponse obj=new TicketAvailResponse();
		  obj.setAvailAble(avail_seats);
    	  obj.setDate((date));
    	  obj.setClasss(classs);
    	  return obj;
		
		

		
	}


}
