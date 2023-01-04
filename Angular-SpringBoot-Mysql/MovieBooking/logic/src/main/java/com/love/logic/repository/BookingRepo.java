package com.love.logic.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.love.logic.models.Bookings;



@Repository
public interface BookingRepo extends JpaRepository<Bookings, String> {

	@Query(value="SELECT * FROM booking   WHERE booking_date=:date  AND mobile_no =:mobile ",nativeQuery=true)
	List<Bookings> getMyTickets(LocalDate date, long mobile);
	
	@Query(value="SELECT SUM(no_od_members) FROM booking   WHERE movie_details_movie_id=:movieId  AND mobile_no =:mobile ",nativeQuery=true)
	int getHow_many_tickets_booked(long mobile, String movieId);
	
	@Query(value="SELECT COUNT(*) FROM booking   WHERE movie_details_movie_id=:movieId  AND mobile_no =:mobile ",nativeQuery=true)
	int getHow_many_tickets_booked_cound(long mobile, String movieId);


	@Query(value="SELECT * FROM booking   WHERE movie_details_movie_id=:movieId ORDER BY booking_date DESC, booking_time DESC limit :from,:range",nativeQuery=true)
	List<Bookings> findAllByMovieTicketMovieId(String movieId, Integer from, Integer range);
	

}
