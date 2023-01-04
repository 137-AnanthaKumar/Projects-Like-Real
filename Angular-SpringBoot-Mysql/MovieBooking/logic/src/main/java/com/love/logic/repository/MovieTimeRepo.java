package com.love.logic.repository;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.love.logic.models.MovieTime;



@Repository
public interface MovieTimeRepo extends JpaRepository<MovieTime, String> {


	
	//NOT USED
	@Query(value="select case when exists(select * from movies WHERE date=:date AND time=:time AND screen_screen_id =:screenNo) then 'true' else 'false' end from movies  ",nativeQuery=true)
	 boolean isCorrectTime(LocalDate date, LocalTime time, Integer screenNo);
	

	
	
	@Query(value="SELECT count(*) FROM movies   WHERE date=:date  AND  time=:time AND screen_screen_id =:screenNo ",nativeQuery=true)
	Integer getMovie(LocalDate date,LocalTime time, Integer screenNo);
	
	
	//WORKING FINE
	@Query(value="SELECT time FROM movies   WHERE date=:date  AND screen_screen_id =:screenNo ",nativeQuery=true)
	List<Time> getMovie(LocalDate date, Integer screenNo);
	
	
	//WORKING FINE
	@Query(value="select COUNT(*) from movies WHERE screen_screen_id= :screenNo",nativeQuery=true)
	public Integer isCorrectTime( Integer screenNo);


	@Query(value="SELECT * FROM movies   WHERE date=:date and booking_opened=true ",nativeQuery=true)
	List<MovieTime> getAllMovieForThisDate(LocalDate date);
	
	@Query(value="SELECT * FROM movies   WHERE date=:date ",nativeQuery=true)
	List<MovieTime> getAllMovieForThisDateAdmin(LocalDate date);



	@Query(value="SELECT time FROM movies m  WHERE date=:date AND movie_name=:movieName AND m.booking_opened=true AND m.time >= CURRENT_TIMESTAMP;",nativeQuery=true)
	List<Time> getAllMovieTimes(String movieName, LocalDate date);
	
	@Query(value="SELECT time FROM movies m  WHERE date=:date AND movie_name=:movieName AND m.booking_opened=true",nativeQuery=true)
	List<Time> getAllMovieTimesOtherdatys(String movieName, LocalDate date);



	@Query(value="SELECT * FROM movies WHERE date=:date AND time=:start AND movie_name=:movieName",nativeQuery=true)
	MovieTime getMovieByTimeDateName(LocalDate date, LocalTime start, String movieName);



	@Query(value="SELECT available_seats FROM movies   WHERE movie_id=:id",nativeQuery=true)
	int getAvailableSeats(String id);

//DELETE FROM table_name WHERE condition;

	@Query(value="SELECT *  FROM movies m  WHERE m.date < NOW();",nativeQuery=true)
	List<MovieTime> deleteOldRecords();



	@Query(value="SELECT * FROM movies m  WHERE  m.date >=  CURDATE()  ",nativeQuery=true)
	List<MovieTime> getAllUpcoming();
//and  m.time > LocalTime();  and  m.time > CURTIME();


	@Query(value="SELECT * FROM movies m  WHERE m.booking_opened=false and m.date >=  CURDATE();",nativeQuery=true)
	List<MovieTime> getAllClosedMovie();



	@Query(value="SELECT * FROM movies m  WHERE m.booking_opened=true and  m.date >=  CURDATE();",nativeQuery=true)
	List<MovieTime> getAllOpenMovie();







	
	

}
