package com.love.logic.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.love.logic.models.MovieTime;
import com.love.logic.payload.request.AddMovieRequest;
import com.love.logic.payload.request.BookingObject;
import com.love.logic.payload.response.Response;
import com.love.logic.payload.response.TodayMovies;
import com.love.logic.security.jwt.JwtUtils;
import com.love.logic.service.MovieService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/movie")
public class MovieController {


	@Autowired
	MovieService service;
	
	@Autowired
	JwtUtils util;
	
	
	@PostMapping("/addMovie/{screenId}/{numofdays}")
	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER')")
	public ResponseEntity<?> newMovie(@RequestHeader(value="authorization",defaultValue = "") String auth,@RequestBody AddMovieRequest movie,@PathVariable Integer screenId,@PathVariable Integer numofdays){
		
	//	AddMovieResponse response=;
		String name=util.getUserNameFromJwtToken(auth.substring(7, auth.length()));
	    return ResponseEntity.ok(service.addNewMovie_step1(movie,screenId,numofdays,name));
		
	}
	
	
	@GetMapping("/getmovie/{id}")
	public ResponseEntity<?> getAllMovieById(@PathVariable String id){
		 Response response=service.getMovie(id);
		 
		    return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/cancelShow/{movieId}")
	public ResponseEntity<?> deleteMovie_show(@PathVariable String movieId){
		String status=service.deleteMovieShow(movieId);
		
		return null;
		
	}
	
	
	@GetMapping("/getMovieforTdy/{date}")
	public ResponseEntity<?> getMoviesForToday(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate  date ){
		TodayMovies res=service.getMoviesForThisDate(date);
		
		return ResponseEntity.ok(res );
		
	}
	
	@GetMapping("/getMovieforTdyforadmin/{date}")
	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER')")
	public ResponseEntity<?> getMoviesForTodayforadmin(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate  date ){
		List<MovieTime> res=service.getmovieadmin(date);
		
		return ResponseEntity.ok(res );
		
	}
	
	@GetMapping("/getAllUpcomingmovie")
	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER') or hasRole('ADMIN')")
	public ResponseEntity<?> getMoviesAllUpcoming( ){
		List<MovieTime> res=service.getAllUpcomingmovieadmin();
		
		return ResponseEntity.ok(res );
		}
	@GetMapping("/getBookingOpendMovie")
	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER') or hasRole('ADMIN')")
	public ResponseEntity<?> getMoviesAllOpend( ){
		List<MovieTime> res=service.getMoviesAllOpend();
		
		return ResponseEntity.ok(res );
		}
	@GetMapping("/getAllBookingClosed")
	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER') or hasRole('ADMIN')")
	public ResponseEntity<?> getAllBookingClosed( ){
		List<MovieTime> res=service.getAllBookingClosed();
		
		return ResponseEntity.ok(res );
		}
	
	@GetMapping("/status/admin/{id}")
	public ResponseEntity<MovieTime> getMovie(@PathVariable String id){
		System.out.println(id);
		return ResponseEntity.ok(service.getMovie1(id));
		}

	
	
	@PutMapping("lockseat/admin/{status}")
	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER') or hasRole('ADMIN')")
      public  ResponseEntity<Response>	 lockAndGet(@RequestBody BookingObject obj,@PathVariable String status){
		if(status.equalsIgnoreCase("path")) {
			System.out.println(obj.getSelectedSeats());
			return ResponseEntity.ok(service.pathSeats(obj));
		}
		else if(status.equalsIgnoreCase("lock")) {
			return ResponseEntity.ok(service.lockSeats(obj));
		}
		else {
			return ResponseEntity.ok(service.unlockSeats(obj));
		}
		
		 
		
	}
	
//	   @PutMapping("unlockseat/admin")
//	   public  ResponseEntity<Response>	 unlockAndGet(@RequestBody BookingObject obj){
//			
//			 
//			
//		}
		
	
	
	
	
	
	
	@GetMapping("getMovieSeatsadmin/lockseat/{movieId}")
	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER') or hasRole('ADMIN')")
	public ResponseEntity<?> getMovieSeatsAdmin( @PathVariable  String movieId  ){

		Response res=service.getMovieDetails(movieId);
	   return ResponseEntity.ok(res);
		
	}
	
	
	@GetMapping("getMovieDetails/{date}/{time}/{movieName}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<?> getMovieSeatsAll(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate  date ,
			@PathVariable  String  time,@PathVariable String movieName  ){

		 LocalTime start = null;
		 
		 
		  SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
	       SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
	       Date date1;
		try {
			date1 = parseFormat.parse(time);
			 System.out.println(parseFormat.format(date1) + " = " + displayFormat.format(date1));
			 start=  LocalTime.parse( displayFormat.format(date1) );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
		
		Response res=service.getMovieDetails(date,start,movieName);
	   return ResponseEntity.ok(res);
		
	}
	
	@GetMapping("/getScreen")
	@PreAuthorize("hasRole('MANAGER') or hasRole('OWNER') or hasRole('ADMIN')")
	public List<Integer> getScreens(){
		return service.getAllScreenNumber();
	}
	
}
