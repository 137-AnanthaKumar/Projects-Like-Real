package com.love.logic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.love.logic.models.Seat;



@Repository
public interface ScreenDetailsRepo extends JpaRepository<Seat, Long> {

	@Query(value="SELECT seat_no FROM seat a  WHERE  a.status=:statu  AND a.movie_id=:movieId ",nativeQuery=true)
	List<String> getAllBookedSeatsNo(String movieId,String statu);

	@Query(value="SELECT seat_no FROM seat a  WHERE  a.status=:statu AND a.movie_id=:movieId ",nativeQuery=true)
	List<String> getBookedSeatsWIthMovie(String movieId, String statu);
	
	@Query(value="SELECT seat_no FROM seat a  WHERE  (a.status=:statu AND a.movie_id=:movieId) OR  a.status=:staus ",nativeQuery=true)
	List<String> getBookedSeatsWIthMovie(String movieId, String statu,String staus);

	@Query(value="SELECT * FROM seat a  WHERE  a.seat_no=:seat AND a.movie_id=:movieId and  a.status='LOCKED' ",nativeQuery=true)
	Seat getSeatRow(String movieId, String seat);
}