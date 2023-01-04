package com.love.logic.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.love.logic.models.Bookings;
import com.love.logic.payload.request.BookingObject;
import com.love.logic.payload.response.Response;
import com.love.logic.service.BookingServise;


@CrossOrigin("*")
@RestController
@RequestMapping("/bookmovieticket")
public class MovieBookingController {
	@Autowired
	BookingServise bookingservise;
	
	@PostMapping("bookticket")
	public ResponseEntity<?> bookMovieTicket(@RequestBody BookingObject obj){
		int booked=bookingservise.canBook(obj.getMobile(),obj.getMovieId());
		System.out.println("here"+booked);
		if(booked<15 && ((15-booked) >= obj.getSelectedSeats().size()) ) {
			Response res=bookingservise.bookTickets(obj);
			return ResponseEntity.ok(res);
		}
		else if(booked==15){
			String msg="Try With Different Mobile Number! You Already Reached Limit";
			return ResponseEntity.ok(new Response(msg, true)); 
		}
		else {
			int a=15-booked;
			String msg=" You can Only Book "+a+"for members or Try With Different Mobile Number";
			return ResponseEntity.ok(new Response(msg, true)); 
			
		}
     	
	}
	
	
	
	@GetMapping("getAllTicketForthisMovie/{movieId}/{from}/{range}")
	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER') or hasRole('ADMIN')")
	public ResponseEntity<?> getMovies( @PathVariable String movieId,@PathVariable Integer from,@PathVariable Integer range){
		List<Bookings> res=bookingservise.getTickets(movieId,from,range);
     	return ResponseEntity.ok(res);
	}
	
	@GetMapping("getticket/{mobile}/{date}")
	public ResponseEntity<?> getMovies(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate  date, @PathVariable long mobile){
		List<Bookings> res=bookingservise.getTicket(date,mobile);
     	return ResponseEntity.ok(res);
	}

}
