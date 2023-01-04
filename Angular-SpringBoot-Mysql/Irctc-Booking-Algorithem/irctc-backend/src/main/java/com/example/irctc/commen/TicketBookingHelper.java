package com.example.irctc.commen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.irctc.dto.response.BookingRequest;
import com.example.irctc.model.Slots;
import com.example.irctc.model.Trip;
import com.example.irctc.repo.SlotRepo;
import com.example.irctc.repo.TripRepo;

@Component
public class TicketBookingHelper {

	
	@Autowired
	private SlotRepo slotsrepo;
	
	@Autowired
	private TripRepo triprepo;
	
	public int get_perfect_position_for_booking(HashMap<Integer, Integer> bookedPerct) {
		
	  	   int bookingSlipt_posi=1; 
    	   if(bookedPerct.size()==1) {
    		   bookingSlipt_posi=1;
    	   }
    	   else if(bookedPerct.size()>1) {
    		   int currentval=bookedPerct.get(1);
        	     //4,0   4     7,4,5,8,2  7,6,0,0,0
        	   for(int va=2;va<=bookedPerct.size();va++) {
        		   
        		   if(currentval>bookedPerct.get(va) && bookedPerct.get(va)!=0) {
        			   currentval=bookedPerct.get(va);
        			   bookingSlipt_posi=va;
        		   }
        		   else if(bookedPerct.get(va)==0) {
        			   bookingSlipt_posi=va;
        			   break;
        		   }
         }
        	  
    	   }
    	   System.out.println(" Booking Position "+bookingSlipt_posi);   
    	   
    	   return bookingSlipt_posi;
    	  
    	
		
		
		
		
	}

	public ArrayList<String> get_perfect_coachFor_booking(HashMap<String, Integer> coachPosiSpiter,
			int splited_position_forBokking) {
		
		ArrayList<String> perfect_coaches=new ArrayList<>();

	    for(Entry<String, Integer> entry: coachPosiSpiter.entrySet()) {
          if(entry.getValue() == splited_position_forBokking) {
	    	  perfect_coaches.add( entry.getKey());
	        System.out.println("The key for value " + splited_position_forBokking + " is " + entry.getKey());
	       
	      }
	    }
	    
	    System.out.println(splited_position_forBokking);
		
		return perfect_coaches;
	}

	public List<Long> get_slots_idFor_fetch(ArrayList<String> perfect_booking_coaches, int noOfPassenger, HashMap<String, Integer> currenAvailabilty,
			 BookingRequest request, String classs) {
		
		List<Long> availAbleSlotId=null;
		boolean status=true;
		for(String coachNo:perfect_booking_coaches) {
			
			if(noOfPassenger<=currenAvailabilty.get(coachNo)) {
				 availAbleSlotId=slotsrepo.getAvailAbleSlotID(coachNo,request.getTripcode(),noOfPassenger);
				System.out.println(availAbleSlotId);
				status=true;
				break;
			}
			else if(noOfPassenger>currenAvailabilty.get(coachNo)) {
				status=false;
			}
			
		}
		
		if(!status) {
			 availAbleSlotId=slotsrepo.getAvailAbleSlotForAll(request.getTripcode(),noOfPassenger,classs);
			
		}
		
		
		
		
		
		lockSlots(availAbleSlotId);
	
	
		
		
		return availAbleSlotId;
	}
	
	
	public void lockSlots(List<Long> availAbleSlotId) {
		  for(Long id:availAbleSlotId) {
			  Slots sl=slotsrepo.getById(id);
			  sl.setSlotStatus("LOCKED");
			  slotsrepo.save(sl);
		  }
		  
		  System.out.println("SLOTS LOCKED");
	}
	
	

	public void unLockSlots(List<Long> slotId) {
		for(Long id:slotId) {
			  Slots sl=slotsrepo.getById(id);
			  sl.setSlotStatus("AVAIL");
			  slotsrepo.save(sl);
		  }
		  System.out.println("SLOTS UN LOCKED");

	}

	public int getStrtingPos(HashMap<String, Integer> coachPositions, String classs) {
		
		int startinPosi=0;
		switch (classs) {
		case "SL":
	        startinPosi=coachPositions.get("S1");
             
		case "3A":
			  startinPosi=coachPositions.get("B1");
           

		case "2A":
			 startinPosi=coachPositions.get("A1");
            
		case "1A":
			 startinPosi=coachPositions.get("AA1");
		}
		return startinPosi;
	}

	

	public String getCoach_starting(String classs) {
		String endingPosi="";
		switch (classs) {
		case "SL":
			endingPosi="S";
			 break;

             
		case "3A":
			endingPosi="B";
			 break;

           

		case "2A":
			endingPosi="A";
			 break;

            
		case "1A":
			 
			endingPosi="AA";
			 break;

		}
		return endingPosi;
	}

	public int get_num_thisCoach(Trip trip, String classs) {
		int numOfCoach=0;
		
		
		switch (classs) {
		case "SL":
			numOfCoach=trip.getTrain().getTotalSeeperClass();
			 break;
        case "3A":
			numOfCoach=trip.getTrain().getTotalThirdClassAc();
			 break;
        case "2A":
			numOfCoach=trip.getTrain().getTotalSecondClassAc();
			 break;
     	case "1A":
	     	numOfCoach=trip.getTrain().getTotalFirstclassAcCouch();
			 break;

		}
		return numOfCoach;
	}

	public int getAvailAble(String tripcode, String classs) {
		// TODO Auto-generated method stub
   String avail="";
		
		
		switch (classs) {
		case "SL":
			avail=triprepo.getAvailableSeats(tripcode);
			 break;
        case "3A":
        	avail=triprepo.getAvailable3ASeats(tripcode);
			 break;
        case "2A":
        	avail=triprepo.getAvailable2Aseats(tripcode);
			 break;
     	case "1A":
     		avail=triprepo.getAvailable1ASeats(tripcode);
			 break;

		}
		
		int avail_final=Integer.parseInt(avail);
		
		
		return avail_final;
	}

	public void updateTripTable(String classs, Trip trip, int sizeOflist) {
		switch (classs) {
		case "SL":
			
			trip.setAvailableSLSeats(String.valueOf(Integer.parseInt(trip.getAvailableSLSeats())-sizeOflist));
			
			 break;
        case "3A":
			trip.setAvailAbleThiredAcSeats(String.valueOf(Integer.parseInt(trip.getAvailAbleThiredAcSeats())-sizeOflist));

			 break;
        case "2A":
        	trip.setAvailableSecondAcseats(String.valueOf(Integer.parseInt(trip.getAvailableSecondAcseats())-sizeOflist));
			 break;
     	case "1A":
     		trip.setAvailableFirstAcseats(String.valueOf(Integer.parseInt(trip.getAvailableFirstAcseats())-sizeOflist));
			 break;

		}
		
	}

	
	
}
