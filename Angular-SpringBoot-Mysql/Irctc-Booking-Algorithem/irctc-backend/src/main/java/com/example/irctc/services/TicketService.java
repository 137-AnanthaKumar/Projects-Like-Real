package com.example.irctc.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.irctc.commen.Helper;
import com.example.irctc.commen.TicketBookingHelper;
import com.example.irctc.dto.response.BookingRequest;
import com.example.irctc.dto.response.PassengerReviewResponse;
import com.example.irctc.dto.response.TicketAvailResponse;
import com.example.irctc.model.Passenger;
import com.example.irctc.model.Slots;
import com.example.irctc.model.Ticket;
import com.example.irctc.model.Trip;
import com.example.irctc.model.User;
import com.example.irctc.payment.PaymentConfig;
import com.example.irctc.repo.PassengerRepo;
import com.example.irctc.repo.RailwayStationRepo;
import com.example.irctc.repo.SlotRepo;
import com.example.irctc.repo.TicketRepo;
import com.example.irctc.repo.TrainRepo;
import com.example.irctc.repo.TripRepo;
import com.example.irctc.repo.UserRepository;

@Service
public class TicketService {
	
	@Autowired
	private TicketRepo ticketRepo;
	
	@Autowired
	private TrainRepo trainRepo;
	
	@Autowired
	private PassengerRepo psgRepo;
	
	@Autowired
     private UserRepository userRepo;
	
	@Autowired
	private	TripRepo triprepo;
	
	@Autowired
	private SlotRepo slotsrepo;
	
	@Autowired
	private Helper helper;
	
	@Autowired
	private RailwayStationRepo stationRepo;
	
	@Autowired
	private TicketBookingHelper tik_helper;
	
	@Autowired
	private PaymentConfig payment;
	

	public Ticket BookNewTicket(BookingRequest request, String classs) {
	    
		ArrayList<String> perfect_booking_coaches=null;
		List<Long> slotId=null;
		
	    Trip trip=triprepo.getOne(request.getTripcode());
	    String tripId=request.getTripcode();
		int avail=tik_helper.getAvailAble(request.getTripcode(),classs);
		System.out.println("AVAILABLE ticket"+avail);
	    if(request.getPassengers().size()<=avail) {
	    	
	      
	        int num_of_class=tik_helper.get_num_thisCoach(trip,classs);
            HashMap<String, Integer> currenAvailabilty = new HashMap<String, Integer>();
	        HashMap<String, Integer> bookedTickets = new HashMap<String, Integer>();
            HashMap<String,Integer> coachPositions=new HashMap<String,Integer>();
            HashSet<String> coaches=slotsrepo.getAllCoachNameByTripId(tripId);
            HashMap<Integer,Integer> spliters=new HashMap<Integer, Integer>();// COACH SPLITER FOR MIND CALCULATION ANAND
            HashMap<Integer,Integer> totalBokkedInSpliters=new HashMap<Integer, Integer>();// TOTAL BOOKED SLOTS IN ABOVE SPLITER
            HashMap<Integer,Integer> bookedPerct=new HashMap<Integer, Integer>();//TOTAL PERCENTAGE SLOTS IN ABOVE SPLITER
            HashMap<String,Integer> coachPosiSpiter=new HashMap<String, Integer>();//COACH NUMBER AND SPLIT POSITION
		
		//SPLITING AVAIABLILITY
		   for(String coachNo:coaches) {
			int count=slotsrepo.getBookedtickets(tripId,coachNo);
	        bookedTickets.put(coachNo, count);
			int count2=slotsrepo.getAvailtickets(tripId,coachNo);
		    currenAvailabilty.put(coachNo, count2);
		    int postion=slotsrepo.getPosition(tripId,coachNo);
		    coachPositions.put(coachNo, postion);
		      }
	
	         String coach_no_starting=tik_helper.getCoach_starting(classs);
	      
		 
 
//                int fullsplited=11/3;               //addition of partialSplited ,fullsplited ll be num_of_sl
//                int partialSplited=11%3;
            

                int fullsplited=num_of_class/3;               //addition of partialSplited ,fullsplited ll be num_of_sl
                int partialSplited=num_of_class%3; 
                if(fullsplited!=0) {
                	  int i=0;
                      while(fullsplited>i) {
                      	
                      	spliters.put(i+1, 3);
                           i++;
                           if(i==fullsplited && partialSplited!=0) {
                          	 spliters.put(i+1,partialSplited);
                           }
                      	
                     }
                }
                else {
                	spliters.put(1, num_of_class);
                }
                
     
                int j=1;
                int coach_num=1;
             
                int totalSplit=spliters.size();
   //==>             //GET TOTAL AND PECENTAGE FOR SPLIT
                while(totalSplit>=j) {
                	int total=0;
                 	System.out.println("SPLIT "+j);
                	
                	int g=1;
                    //First 3 sl Coach booked addition
                	while(g<=spliters.get(j)) {
                		System.out.println("SPLIT CoACH "+g);
                		
                		int thisoachbooked=bookedTickets.get(coach_no_starting+coach_num);
                		System.out.println("thisoachbooked "+thisoachbooked);
                		if(thisoachbooked!=0) {
                    		System.out.println(coach_no_starting+coach_num);

                		}
                		coachPosiSpiter.put(coach_no_starting+coach_num,j );
                		total=total+thisoachbooked;
                	    g++;
                		coach_num++;
                     }
                	
                	float b=((float)total)/((float)(spliters.get(j)*80));
            		int percent=(int)(b*100);
                	bookedPerct.put(j, percent);
                 	totalBokkedInSpliters.put(j, total);
                    j++;
                }

    //==>            
              int splited_position_forBokking= tik_helper.get_perfect_position_for_booking(bookedPerct);
              System.out.println("splited_position_forBokking"+splited_position_forBokking);
              
  //==>>    
              perfect_booking_coaches=tik_helper.get_perfect_coachFor_booking(coachPosiSpiter,splited_position_forBokking);
              System.out.println("coches");
              System.out.println(perfect_booking_coaches);
              slotId=tik_helper.get_slots_idFor_fetch(perfect_booking_coaches,request.getNoOfPassenger(),currenAvailabilty,request,classs);  

	    }
	    else {
	       slotId=slotsrepo.getAvailAbleSlotForAll(tripId, avail, classs);
    	
	    }
              
        
              
              
              
              
              Boolean paymentStatus=payment.MakePayment();
              if(paymentStatus) {
            	  
            	  Ticket ticket=bookTicket(classs,request,slotId,trip);
            	 
            	  
            	  return ticket;
            	  
              }
              else {
            	  tik_helper.unLockSlots(slotId);
              }
            return null;
	}
	
	
	
	
	public Ticket bookTicket(String classs, BookingRequest request, List<Long> slotId, Trip trip) {
		User user=userRepo.getById(request.getUser_id());
		
		Ticket ticket=new Ticket();
		ticket.setClasaa(classs);
		ticket.setClassType(request.getClassType());
		ticket.setPnr(helper.getNewPnr());
		ticket.setBookedDate(LocalDate.now());
		ticket.setBookedTime(LocalTime.now());
		ticket.setNoOfPassenger(request.getNoOfPassenger());
		ticket.setPaymentMode(request.getPaymentMode());
		ticket.setUser(user);
		ticket.setMobileNo(request.getMobileNo());
		ticket.setTrip(trip);
		

		
	
		int sizeOflist=slotId.size();
		int initiate=0;
		for(Passenger p:request.getPassengers()) {
			System.out.println("INI "+initiate);
			Passenger pasg=new Passenger();
		    pasg.setGender(p.getGender());
			pasg.setPassegerId(helper.getRandomNum(8));
			pasg.setPassengerAge(p.getPassengerAge());
			pasg.setPassengerName(p.getPassengerName());
			
			
			
			if((initiate+1)!=sizeOflist || sizeOflist!=0) {
				pasg.setTicketStatus("CNF");
				Slots slot=slotsrepo.getById(slotId.get(initiate));
				slot.setSlotStatus("BOOKED");
				slot.setPassId(pasg.getPassegerId());
				//slot.setPass(pasg);
				slot=slotsrepo.save(slot);
				
				pasg.setSlot(slot);
			}
			else {
				pasg.setTicketStatus("WL"+String.valueOf(trip.getWaitList()+1));
				trip.setWaitList(trip.getWaitList()+1);
				  triprepo.save(trip);
			}
			
			
			
			//pasg.setSlot(slot);
			
			ticket.addPlayer(pasg);
			
			
			//pasg.set
			
			initiate++;
		}
		
		   tik_helper.updateTripTable(classs,trip,sizeOflist);
		   triprepo.save(trip);
				ticket=ticketRepo.save(ticket);
				
		
		
		return ticket;
		
	}




	public PassengerReviewResponse getPagReview(String tripId, String classType, String coachType) {

		Trip trip=triprepo.getOne(tripId);
		TicketAvailResponse res=null;
		PassengerReviewResponse finalResponse=new PassengerReviewResponse();
		if(classType.equalsIgnoreCase("GENERAL")) {
			System.out.println("hi 1");
			 res=helper.fetchAvailTicketForReview(trip, coachType);

		}
		if(classType.equalsIgnoreCase("TATKAL")) {
			System.out.println("hi 2");
			 res=helper.fetchAvailTicketForReview(trip, coachType);

		}
		finalResponse.setAvailSta(res.getAvailAble());
		finalResponse.setClassType(classType);
		finalResponse.setCoachType(coachType);
		String code=trip.getFromStation();
		String fromStation=stationRepo.getStationName(trip.getFromStation());
		String toStation=stationRepo.getStationName(trip.getToStaion());
		
		finalResponse.setFromSta(fromStation);
		finalResponse.setToSta(toStation);
	
		LocalDate date=trip.getDateOfJourney();
		
		String eoj_date=trip.getEndOfJourney().toString();
	    String doj_datee=date.toString();
	    finalResponse.setDateOfJourney(doj_datee);
	    finalResponse.setEndOfJourney(eoj_date);
	    
	    DateTimeFormatter formatter= DateTimeFormatter.ISO_TIME;
	    String sta_time=trip.getStartTime().format(formatter);
	    finalResponse.setStartTime(sta_time.substring(0, 5));
	    finalResponse.setEndTime(trip.getEndTime().format(formatter).substring(0, 5));
	    finalResponse.setTrainName(trip.getTrain().getTrainName());
	    finalResponse.setTrainNo(trip.getTrain().getTrainNo());
		return finalResponse;
	}
	
	
	public boolean cancelTicket(long pnr,List<String> passengerId) {
		Ticket ticket=ticketRepo.getOne(pnr);
		for(String id:passengerId) {
			Passenger psg=psgRepo.getById(id);
			Slots slot=slotsrepo.getPassenger(id);
			
			
			slot.setSlotStatus("CANCELLED");
		    psg.setSlot(null);
		    psg.setTicketStatus("CAN");
		    db_for_canceledticket(slot.getTrip().getTripcode(),ticket.getClassType(),ticket.getClasaa());
		    psgRepo.save(psg);
		    slotsrepo.save(slot);
		}
		
		
		return true;
	}
	
	public void db_for_canceledticket(String tripCode,String clas, String clasType) {
		
	}
	

}
