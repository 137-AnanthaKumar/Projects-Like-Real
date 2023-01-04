package com.example.irctc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.irctc.dto.response.MessageResponse;
import com.example.irctc.dto.response.PnrPassRespo;
import com.example.irctc.dto.response.PnrResponse;
import com.example.irctc.dto.response.StationSearchResponse;
import com.example.irctc.exception.TrainNameAndNoAvailable;
import com.example.irctc.model.Passenger;
import com.example.irctc.model.RailwayStation;
import com.example.irctc.model.Ticket;
import com.example.irctc.repo.RailwayStationRepo;
import com.example.irctc.repo.TicketRepo;

@CrossOrigin("*")
@RestController
@RequestMapping("/irctc/enquiry")
public class EnquiryController {
	
	@Autowired 
	private TicketRepo tikrepo;
	
	@Autowired
	RailwayStationRepo stationRepo;
	
	@GetMapping("/pnrsearch/{pnr}")
	public ResponseEntity<?> pnrSearch(@PathVariable Long pnr){
		
		Ticket ticket=tikrepo.findByPnr(pnr);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ticket!=null) {
			PnrResponse res=new PnrResponse();
			res.setMsg(true);
			res.setDateOfJourney(ticket.getTrip().getDateOfJourney());
			res.setDateOfbooking(ticket.getBookedDate());
			res.setFromSta(ticket.getTrip().getFromStation());
			res.setToSta(ticket.getTrip().getToStaion());
			res.setPnr(ticket.getPnr());
			res.setTrainNo(ticket.getTrip().getTrain().getTrainNo());
			int i=1;
			for(Passenger obj:ticket.getPassengers()) {
				PnrPassRespo pase=new PnrPassRespo();
				pase.setPasse("PASSANGER "+i);
	            pase.setSeatNo((obj.getSlot().getCoachNo()+"/"+obj.getSlot().getSeatNo()));
				res.getPassengers().add(pase);
				i++;
			}
			
			
			return ResponseEntity.ok(res);
		}
		else {
			PnrResponse res=new PnrResponse();
			res.setMsg(false);
			return ResponseEntity.ok(res);
		}
		
	}
	
	@GetMapping("/stations/{letter}")
	public ResponseEntity<?> getStations(@PathVariable String letter){
		
		List<RailwayStation> station=stationRepo.getStartWith(letter);
		//station.stream().m
		System.out.println(station.size());
		StationSearchResponse res=new StationSearchResponse();
		if(station.size()!=0){
			
			for(RailwayStation sta:station) {
				System.out.println("statings 1");
				res.getStations().add(sta.getStationName()+"-"+sta.getStationCode());
			}
			return ResponseEntity.ok(res);
			
		}
		else {
			return ResponseEntity.ok(res);
		}
		
		
		
		
		
		
	}
	
	

}
