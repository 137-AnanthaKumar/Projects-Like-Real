package com.example.irctc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.irctc.commen.Helper;
import com.example.irctc.dto.response.BookingRequest;
import com.example.irctc.dto.response.PassengerResponse;
import com.example.irctc.dto.response.PassengerReviewResponse;
import com.example.irctc.dto.response.TicketAvailResponse;
import com.example.irctc.dto.response.TicketResponse;
import com.example.irctc.dto.response.TrainSearchResponse;
import com.example.irctc.exception.TrainNameAndNoAvailable;
import com.example.irctc.model.Passenger;
import com.example.irctc.model.Ticket;
import com.example.irctc.model.Train;
import com.example.irctc.model.Trip;
import com.example.irctc.repo.RailwayStationRepo;
import com.example.irctc.repo.SlotRepo;
import com.example.irctc.repo.TripRepo;
import com.example.irctc.services.BookingService;
import com.example.irctc.services.TicketService;


@CrossOrigin("*")
@RestController
@RequestMapping("/irctc/booking")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private TicketService ticSer;
	
	@Autowired 
	private SlotRepo slotrepo;
	

	
	@Autowired
	Helper helper;
	
	@Autowired
	private TripRepo tripRep;
    
	
	@GetMapping("/searchtrain/{fromStation}/{toStation}/{date}/{classs}/{classType}")
	public ResponseEntity<?> search_train(@PathVariable String fromStation,@PathVariable String toStation, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate  date,@PathVariable String classs,@PathVariable String classType) throws ParseException{
		System.out.println("GEHJEJ");

		System.out.println(date);
		String[] sta1=fromStation.split("-");
		String[] sta2=toStation.split("-");
		
		String fromSta=sta1[sta1.length-1];
		String toSta=sta2[sta2.length-1];
		
		
		
		List<Trip> trains=bookingService.getTrains(fromSta,toSta,date);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(trains.size()==0) {
			
			return ResponseEntity.ok(new TrainNameAndNoAvailable("NOTFOUND"));
	  }
		List<TrainSearchResponse> train1=new ArrayList<TrainSearchResponse>();
	   // List<TicketAvailResponse> avail=new ArrayList<TicketAvailResponse>();
		for(Trip train:trains) {
			HashSet<String> classTypes=slotrepo.getAllCoachType(train.getTripcode());
			 List<TicketAvailResponse> avail=helper.fetchAvailTicket(train,classTypes);
			
			TrainSearchResponse obj=new TrainSearchResponse(train.getFromStation(),train.getToStaion(),train.getDateOfJourney(),train.getEndOfJourney(),train.getStartTime(),train.getEndTime(),train.getTrain().getTrainNo(),train.getTrain().getTrainName(),train.getTripcode());
			obj.setClasses(classTypes);
			obj.setAvaiability(avail);
			obj.setClaass(classs);
			if(classType.equalsIgnoreCase("GEN")) {
				obj.setClassType("GENERAL");
			}
			if(classType.equalsIgnoreCase("TATK")) {
				obj.setClassType("TATKAL");
			}
			
			
			train1.add(obj);
		}
		
		
		return ResponseEntity.ok(train1);
	}
//	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	
	@GetMapping("/avail/{trainNo}/{fromStation}/{toStation}/{date}/{classs}")
	public ResponseEntity<?> avaiable(@PathVariable int trainNo,@PathVariable String fromStation,@PathVariable String classs,@PathVariable String toStation, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate  date){
		String[] sta1=fromStation.split("-");
		String[] sta2=toStation.split("-");
		
		String fromSta=sta1[sta1.length-1];
		String toSta=sta2[sta2.length-1];
		TicketAvailResponse avai= bookingService.getAvailAbility(trainNo,fromSta,toSta,date,classs);
		  
		return ResponseEntity.ok(avai);
		
	}
	
	@GetMapping("/avaiability/{tripId}/{clas}")
	public ResponseEntity<?> fetchTrip_booking(@PathVariable String tripId,@PathVariable String clas){
		return null;
		
	}
	
	
//	@PostMapping("/bookticket/{trainNo}/{fromStation}/{toStation}/{date}/{classs}")

	
	@PostMapping("/bookticket/{classs}")
	public ResponseEntity<?> ticket_booking(@RequestBody BookingRequest request,@PathVariable String classs){
		   
		
		Ticket ticket=ticSer.BookNewTicket(request,classs);
		TicketResponse res=new TicketResponse();
		for(Passenger pas:ticket.getPassengers()) {
			PassengerResponse obj=new PassengerResponse();
			obj.setCoachNo(pas.getSlot().getCoachNo());
			obj.setGenter(pas.getGender());
			obj.setCoachType(pas.getSlot().getCoachType());
			obj.setStatus(pas.getTicketStatus());
			obj.setSeatNo(pas.getSlot().getSeatNo());
			obj.setAge(pas.getPassengerAge());
		
			obj.setName(pas.getPassengerName());
			res.getPass().add(obj);

			
		}
		PassengerReviewResponse rff=ticSer.getPagReview(ticket.getTrip().getTripcode(),"GENERAL",classs);
		
		res.setDetails(rff);
		res.setPnr(ticket.getPnr());
		res.setFromSta(ticket.getTrip().getFromStation());
		res.setToSta(ticket.getTrip().getToStaion());
		
		return ResponseEntity.ok(res);
	}
	
	@GetMapping("/passReview/{tripId}/{classType}/{coachType}")
	public ResponseEntity<?> getPsgReviewRes(@PathVariable String tripId ,@PathVariable String classType,@PathVariable String coachType){
		
		PassengerReviewResponse finalres=ticSer.getPagReview(tripId,classType,coachType);
		 return ResponseEntity.ok(finalres);
		
	}
	

	
	
}
