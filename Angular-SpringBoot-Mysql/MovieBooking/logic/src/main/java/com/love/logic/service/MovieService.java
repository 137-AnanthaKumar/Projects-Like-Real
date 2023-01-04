package com.love.logic.service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.love.logic.models.MovieTime;
import com.love.logic.models.ScreenDetails;
import com.love.logic.models.Seat;
import com.love.logic.models.workersDetails;
import com.love.logic.payload.request.AddMovieRequest;
import com.love.logic.payload.request.BookingObject;
import com.love.logic.payload.request.SeatAvailabily;
import com.love.logic.payload.response.AddMovieResponse;
import com.love.logic.payload.response.MovieListHome;
import com.love.logic.payload.response.Response;
import com.love.logic.payload.response.TodayMovies;
import com.love.logic.repository.MovieTimeRepo;
import com.love.logic.repository.ScreenDetailsRepo;
import com.love.logic.repository.ScreenRepo;
import com.love.logic.repository.TrackingRepo;
import com.love.logic.repository.WorkersRepo;


@Service
public class MovieService {
	
	@Autowired
	MovieTimeRepo movieRepo;
	
	@Autowired
	WorkersRepo workerrepo;
	
	@Autowired
	ScreenRepo screenRepo;
	 
	  @Autowired
	  ActivityTracking tracking;
	
	@Autowired
	ScreenDetailsRepo seatRepo;
	public AddMovieResponse addNewMovie_step1(AddMovieRequest movie, Integer screenId, Integer numofdays,String name) {
		MovieTime obj=new MovieTime();
		obj.setDate(movie.getDate());
		obj.setDescription(movie.getDescription());
		obj.setMovieCast(movie.getMovieCast());
		obj.setMovieName(movie.getMovieName());
		obj.setMovieStatus(movie.getMovieStatus());
		 LocalTime start = LocalTime.parse( movie.getTime() );
		obj.setTime(start);
		obj.setPrize(movie.getPrize());
		obj.setTrainlarUrl(movie.getTrainlarUrl());
		obj.setBookingOpened(movie.getBookingOpened());
		
		
		return addNewMovie(obj,screenId,numofdays,name);
	}


	public AddMovieResponse addNewMovie(MovieTime movie, Integer screenNo,Integer numofdays,String name) {
		int num_of_days=numofdays;
		LocalDate date=movie.getDate();
		List<LocalDate> failed=new ArrayList<LocalDate>();
		List<LocalDate> already=new ArrayList<LocalDate>();
		List<LocalDate> sucess=new ArrayList<LocalDate>();
		//System.out.println(da.plusDays(num_of_days));
		//boolean time_perfect=false;
		for(int i=0;i<numofdays;i++) {
			    LocalDate da=date.plusDays(i);
			    if(movieRepo.getMovie(da,movie.getTime(),screenNo)==0) {
					
					System.out.println("fine32");
					ScreenDetails screen=screenRepo.getById(screenNo);
					List<Time> mov=movieRepo.getMovie(da,screenNo);
					System.out.println("66688888");
					System.out.println(mov.size());
					
					boolean res=perfectTime(movie.getTime(),mov);
			
             	if (res) {
             		
						movie.setDate(da);
						movie.setAvailableSeats(screen.getTotalSeats());
						movie.setMovieId(password());
						movie.setAddedTime(LocalTime.now());
						movie.setScreen(screen);
						//movie.setAvailableSeats(seatRepo.getTotalSeats())
						movieRepo.save(movie);
						 
						sucess.add(da);
						pathLockForNewMovie(movie.getMovieId());
						 tracking.trackActivity("NEWMOVIESHOW",getCodeForWorker(name),name);
				}else{
					 failed.add(da);
				}
             	
			  
		 			
		       }
			    
			    if(movieRepo.getMovie(da,movie.getTime(),screenNo)!=0) {
			    	already.add(da);
			    }
			  
				 
			  }
		 String msg="";
		 
         
		 return new AddMovieResponse("SUCCESS",sucess,already,failed);
		
		
		
}
	
	public Integer getCodeForWorker(String name) {
		
		//int workerCode=
		return workerrepo.getWorkerCodeByName(name);
	}

	private void pathLockForNewMovie(String movieId) {
	String[] a= {"B","C","D","E","F","G","I","H","J","K","L","M","N","O","P"};
	for(int i=0;i<a.length;i++) {
		for(int j=1;j<4;j++) {
			Seat obj=new Seat();
			obj.setMovieId(movieId);
			obj.setSeatId(password());
			obj.setSeatNo(a[i]+(6+j));
			obj.setSeatLine(a[i]+(6+j));
			obj.setStatus("PATH");
			seatRepo.save(obj);
			
		}
		for(int j=1;j<4;j++) {
			Seat obj=new Seat();
			obj.setMovieId(movieId);
			obj.setSeatId(password());
			obj.setSeatNo(a[i]+(20+j));
			obj.setSeatLine(a[i]+(20+j));
			obj.setStatus("PATH");
			seatRepo.save(obj);
			
		}
		
		if(a[i].equalsIgnoreCase("I")) {
		
		}
		
		
	}
	
	for(int k=10;k<20;k++) {
		Seat obj=new Seat();
		obj.setMovieId(movieId);
		obj.setSeatId(password());
		obj.setSeatNo("I"+k);
		obj.setSeatLine("I"+k);
		obj.setStatus("PATH");
		seatRepo.save(obj);
	}
		
	}


	public boolean perfectTime(LocalTime da, List<Time> mov) {
		boolean time_perfect = true;
		for(Time time:mov) {
			System.out.println("66688888");
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	        LocalTime tim=LocalTime.parse(sdf.format(time));
            Duration durationAfter = Duration.between(  da,tim );
	        Duration durationBefore = Duration.between( tim,  da );
	        int time_1=Math.round((float)(Math.abs(durationAfter.getSeconds())/3600f));
	        int time_2=Math.round((float)(Math.abs(durationBefore.getSeconds())/3600f));
	        
	        System.out.println(" after "+Math.round((float)(Math.abs(durationAfter.getSeconds())/3600f)));
	        System.out.println(" before "+Math.round((float)(Math.abs(durationBefore.getSeconds())/3600f)));
	        
	       if(3<=time_1 && 3<=time_2) {
	    	   System.out.println("perfect Time");
	    	   time_perfect=true;
	    	 
	       }
	       else {
	    	   time_perfect=false;
	    	   return false;
	    	   }
	          }
		return time_perfect;
	}
	
	public String password() {
		 String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	      String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
	      String specialCharacters = "@$";
	      String numbers = "1234567890";
	      String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
	      Random random = new Random();
	      int length_of_random=8;
	      char[] password = new char[length_of_random];

	      password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
	      password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
	      password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
	      password[3] = numbers.charAt(random.nextInt(numbers.length()));
	   
	      for(int i = 4; i< length_of_random ; i++) {
	         password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
	      }
	      
	      String pass=String.valueOf(password);
		return pass;
	}

	public Response getMovie(String id) {
		
		MovieTime movie=movieRepo.findById(id).get();
		if(movie==null) {
			return new Response("Movie NOt Found",true);
		}
		else {
			return new Response("Movie Found",false,movie);
		}
		
	
	}
	
	public List<MovieTime> getmovieadmin(LocalDate date){
		return movieRepo.getAllMovieForThisDateAdmin(date);
	}
	public List<MovieTime> getmoviea(LocalDate date){
		return movieRepo.getAllMovieForThisDate(date);
	}
	
	
public TodayMovies getMoviesForThisDate(LocalDate date) {
		
		List<MovieTime> res=getmoviea(date);
		
		ArrayList<MovieListHome> listOfMovies=new ArrayList<MovieListHome>();
		if(res.size()!=0) {
			boolean canadd=true;
			
			for(MovieTime resObj:res) {
				 
				MovieListHome obj=new MovieListHome();
						obj.setMovieId(resObj.getMovieId());
						obj.setMovieName(resObj.getMovieName());
						List<Time> times;
						ArrayList<String> timeString=new ArrayList<String>();
						//List<String> timeString;
						if(date.equals(LocalDate.now())) {
						
							 times=movieRepo.getAllMovieTimes(resObj.getMovieName(),date);
						}
						else {
						
							times=movieRepo.getAllMovieTimesOtherdatys(resObj.getMovieName(),date);
						}
						
						System.out.println(times);
						
						DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
				    	//String dateString = dateFormat.format(new Date()).toString();
				    	for(Time t:times) {
				    		String timwAmPm = dateFormat.format(t).toString();
				    		System.out.println(timwAmPm);
				    		timeString.add(timwAmPm);
				    	}
						//obj.setTimes(times);
						obj.setTimes(timeString);
						
						
						if(canAdd(listOfMovies,resObj.getMovieName()) && obj.getTimes().size()!=0) {
							
							listOfMovies.add(obj);
							System.out.println(" movie name "+obj.getMovieName());
						    
			        	
			           }
				
				

				
			}		
			
			
			return new TodayMovies("Movie  FOUND",false,listOfMovies);
		}
		else {
			return new TodayMovies("Movies Not FOUND",true,listOfMovies);
		}
		
		
		
		
	}


 public boolean canAdd(ArrayList<MovieListHome> listOfMovies, String movieName) {
	boolean status=true;
	for(MovieListHome list:listOfMovies) {
		if(list.getMovieName().equalsIgnoreCase(movieName) ) {
			System.out.println("Movie=="+movieName+" "+list.getMovieName());
			status=false;
			break;
		}
		else {
			status=true;
		}
	}
	
		return status;
}

public Response properResponseForSeatPage(MovieTime movie,List<String> seats) {
	Response obj=new Response();
if(movie==null) {
		
		obj.setErrormsg("Movie NotFOund");
		obj.setError(true);
		return obj;
	}else {
		SeatAvailabily obj1=new SeatAvailabily(movie.getMovieId(), movie.getMovieName(), movie.getDescription(),movie.getMovieCast(), movie.getPrize(),
				movie.getTime(), movie.getDate(),movie.isBookingOpened(), movie.getMovieStatus());
		obj1.setScreen(movie.getScreen().getScreenId());
		List<String> bookedSeats=seatRepo.getAllBookedSeatsNo(movie.getMovieId(),"BOOKED");
		List<String> path_seats=seatRepo.getBookedSeatsWIthMovie(movie.getMovieId(),"PATH");
		obj1.setPathseats(path_seats);
		if(seats==null) {
			List<String> locked_seats=seatRepo.getBookedSeatsWIthMovie(movie.getMovieId(),"LOCKED");
			
			bookedSeats.addAll(locked_seats);
		
		}
		
	//	System.out.println("bookedseats"+bookedSeats.size());
		obj1.setTrainlerUrl(movie.getTrainlarUrl());
		obj.setErrormsg("Movie FOund");
		obj.setError(false);
		obj1.setBookedseats(bookedSeats);
		obj1.setLockedseats(seats);
		obj.setObj2(obj1);
		return obj;
	}
	
}
public Response getMovieDetails(String movieId) {
	MovieTime movie=movieRepo.findById(movieId).get();
	List<String> locked_seats=seatRepo.getBookedSeatsWIthMovie(movie.getMovieId(),"LOCKED");
	return properResponseForSeatPage(movie,locked_seats);
}
public Response getMovieDetails(LocalDate date, LocalTime start, String movieName) {
	
	
	
	System.out.println(movieName);
	MovieTime movie=movieRepo.getMovieByTimeDateName(date,start,movieName);
	
	  return properResponseForSeatPage(movie,null);
	
//	return obj;

}

public String deleteMovieShow(String movieId) {
	
	
	
//	System.out.println(movieId+" here");
	MovieTime onj=movieRepo.getById(movieId);
	onj.setScreen(null);
	movieRepo.save(onj);
	
//	System.out.println(onj.getAvailableSeats());
//	movieRepo.delete(onj);
	System.out.println("suc");
	movieRepo.deleteById(movieId);
	
	return null;
}


public List<Integer> getAllScreenNumber() {
	// TODO Auto-generated method stub
	return screenRepo.getAllScreenNumber();
}


public List<MovieTime> getAllUpcomingmovieadmin() {
	// TODO Auto-generated method stub
	return movieRepo.getAllUpcoming();
}


public void statuschange(String id) {
	MovieTime mov=movieRepo.getById(id);
	
	if(mov.isBookingOpened()) {
		mov.setBookingOpened(false);
	}
	else {
		mov.setBookingOpened(true);
	}
	movieRepo.save(mov);
}


public MovieTime getMovie1(String id) {
	
   MovieTime mov=movieRepo.getById(id);
	
	if(mov.isBookingOpened()) {
		mov.setBookingOpened(false);
	 }
	else {
		mov.setBookingOpened(true);
	}
	movieRepo.save(mov);
	return mov;
}


public List<MovieTime> getMoviesAllOpend() {
	// TODO Auto-generated method stub
	return movieRepo.getAllOpenMovie();
}


public List<MovieTime> getAllBookingClosed() {
	// TODO Auto-generated method stub
	return movieRepo.getAllClosedMovie();
}


public Response lockSeats(BookingObject obj) {
	for(String seat:obj.getSelectedSeats()) {
		
		Seat seat_obj=new Seat();
		if(seatRepo.getSeatRow(obj.getMovieId(),seat)==null){
			seat_obj.setSeatId(password());
			seat_obj.setStatus("LOCKED");
			seat_obj.setSeatNo(seat);
			seat_obj.setSeatLine(seat);
			seat_obj.setMovieId(obj.getMovieId());
			seatRepo.save(seat_obj);
		}
		
		
	}
	
	
	
	return getMovieDetails(obj.getMovieId());
}

public Response unlockSeats(BookingObject obj) {
	
	for(String seat:obj.getSelectedSeats()) {
		
		Seat seat_obj=seatRepo.getSeatRow(obj.getMovieId(),seat);
	
		seatRepo.delete(seat_obj);
		System.out.println("deleted");
	}
	
	
	
	return getMovieDetails(obj.getMovieId());
}


   public Response pathSeats(BookingObject obj) {
		for(String seat:obj.getSelectedSeats()) {
			Seat ne=seatRepo.getSeatRow(obj.getMovieId(),seat);
			Seat seat_obj=new Seat();
			if(ne==null){
				seat_obj.setSeatId(password());
				seat_obj.setStatus("PATH");
				seat_obj.setSeatNo(seat);
				seat_obj.setSeatLine(seat);
				seat_obj.setMovieId(obj.getMovieId());
				seatRepo.save(seat_obj);
			}
			
			else if(ne.getStatus().equalsIgnoreCase("LOCKED")) {
				ne.setStatus("PATH");
				seatRepo.save(ne);
			}
			
			
		}
		
	return getMovieDetails(obj.getMovieId());
  }





}
