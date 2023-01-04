package com.love.logic.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.love.logic.models.Bookings;
import com.love.logic.models.MovieTime;
import com.love.logic.models.Seat;
import com.love.logic.payload.request.BookingObject;
import com.love.logic.payload.response.Response;
import com.love.logic.repository.BookingRepo;
import com.love.logic.repository.MovieTimeRepo;

import antlr.debug.NewLineEvent;

@Service
public class BookingServise {
	
//	
//	@Autowired
//	ScreenDetailsRepo repo;
	
	@Autowired
	MovieTimeRepo movie;
	
	@Autowired
	BookingRepo tickets;

	public Response bookTickets(BookingObject obj) {
		
		
		
		MovieTime mo=movie.getById(obj.getMovieId());
		if(movie.getAvailableSeats(obj.getMovieId())<obj.getSelectedSeats().size()) {
			return new Response("Failed", false);
		}
		else {
			Bookings booking=new Bookings();
	        booking.setTicketId(getRandomString(8));
			System.out.println(booking.getTicketId());
			booking.setBookingDate(LocalDate.now());
			booking.setBookingTime(LocalTime.now());
			booking.setMailId(obj.getEmailId());
			booking.setMobileNo(obj.getMobile());
			booking.setMovieDetails(mo);
			booking.setNoOdMembers(obj.getSelectedSeats().size());
			System.out.println("54");
			ArrayList<Seat> lists=new ArrayList<Seat>();
			System.out.println("56");
			for(String seatss:obj.getSelectedSeats()) {
				System.out.println("58");
				Seat seat=new Seat();
				seat.setSeatId(getRandomString(10));
				seat.setMovieId(mo.getMovieId());
				seat.setSeatNo(seatss);
				seat.setStatus("BOOKED");
				seat.setSeatLine(seatss);
				lists.add(seat);
				//booking.getSeatsForThisBooking().add(seat);
				
				
			}
			System.out.println("70");
			booking.setSeatsForThisBooking(lists);
			System.out.println("72");
			booking=tickets.save(booking);
			System.out.println("74");
			mo.setAvailableSeats(mo.getAvailableSeats()-obj.getSelectedSeats().size());
			movie.save(mo);
			return new Response("booked", false, booking);
			
			
		}
		

	}
	
	public String getRandomString(int num) {
		 String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	      String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
	      String specialCharacters = "@$";
	      String numbers = "1234567890";
	      String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
	      Random random = new Random();
	      
	      char[] password = new char[num];

	      password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
	      password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
	      password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
	      password[3] = numbers.charAt(random.nextInt(numbers.length()));
	   
	      for(int i = 4; i< num ; i++) {
	         password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
	      }
	      
	      String pass=String.valueOf(password);
		return pass;
	}

	public List<Bookings> getTicket(LocalDate date, long mobile) {
		List<Bookings> tickets_on_date=tickets.getMyTickets(date,mobile);
		if(tickets_on_date.size()!=0) {
			return tickets_on_date;
		}
		return null;
	}

	public Integer canBook(long mobile, String movieId) {
		Integer count=tickets.getHow_many_tickets_booked_cound(mobile,movieId);
		if(count == 0) {
			return count;
		}
		else {
			int a=tickets.getHow_many_tickets_booked(mobile,movieId);
			System.out.println(a);
			return a;
		}
		
	}

	public List<Bookings> getTickets(String movieId, Integer from, Integer range) {
	
		return tickets.findAllByMovieTicketMovieId(movieId,from,range);
	}

}
