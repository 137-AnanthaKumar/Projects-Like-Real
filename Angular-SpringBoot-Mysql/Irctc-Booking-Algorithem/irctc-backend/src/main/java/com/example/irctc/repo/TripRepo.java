package com.example.irctc.repo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.irctc.dto.response.TrainSearchResponse;
import com.example.irctc.model.Trip;
@Repository
public interface TripRepo extends JpaRepository<Trip, String> {
	

//	SELECT * FROM trip t WHERE (t.from_station="TSI" AND  t.to_staion="TBM"  AND t.date_of_journey='2022-10-25');
	
	
	
	@Query(value="SELECT * FROM trip t WHERE (t.from_station=:from_sta AND  t.to_staion=:to_sta  AND t.date_of_journey=:date) ",nativeQuery=true)
    List<Trip> findAllFromStationAndToStationAndDateOfJourney(String from_sta,String to_sta,LocalDate date);
	
	@Query(value="SELECT trip_status FROM trip t WHERE t.from_station=:from_sta AND  t.to_staion=:to_sta  AND t.date_of_journey=:date ",nativeQuery=true)
   public String findByFromStationAndToStationAndDateOfJourney1(String from_sta,String to_sta,LocalDate date);

	@Query(value="SELECT availableslseats FROM trip t  WHERE t.train_number=:trainNo AND t.from_station=:fromStation AND  t.to_staion=:toStation  AND date_of_journey=:date ",nativeQuery=true)
    public String getAvailable(int trainNo, String fromStation, String toStation, LocalDate date);
	
	@Query(value="SELECT avail_able_thired_ac_seats FROM trip t  WHERE t.train_number=:trainNo AND t.from_station=:fromStation AND  t.to_staion=:toStation  AND date_of_journey=:date ",nativeQuery=true)
	public String getAvailable_3AC(int trainNo, String fromStation, String toStation, LocalDate date);

	@Query(value="SELECT available_second_acseats FROM trip t  WHERE t.train_number=:trainNo AND t.from_station=:fromStation AND  t.to_staion=:toStation  AND date_of_journey=:date ",nativeQuery=true)
	public String getAvailable_2AC(int trainNo, String fromStation, String toStation, LocalDate date);
	
	@Query(value="SELECT available_first_acseats FROM trip t  WHERE t.train_number=:trainNo AND t.from_station=:fromStation AND  t.to_staion=:toStation  AND date_of_journey=:date ",nativeQuery=true)
	public String getAvailable_1AC(int trainNo, String fromStation, String toStation, LocalDate date);

	@Query(value="SELECT * FROM trip t WHERE (t.trip_status='TRIPOPENED' AND t.to_staion=:toStation AND t.from_station=:fromStation AND t.date_of_journey=:date) ",nativeQuery=true)
    List<Trip> getAllOpenedTrip(String fromStation, String toStation, LocalDate date);

	
	@Query(value="SELECT avail_tatkalslseats FROM trip t  WHERE t.train_number=:trainNo AND t.from_station=:fromStation AND  t.to_staion=:toStation  AND date_of_journey=:date ",nativeQuery=true)
    String getAvailable_TSL(int trainNo, String fromStation, String toStation, LocalDate date);
	
	@Query(value="SELECT avail_tatkal1acseats FROM trip t  WHERE t.train_number=:trainNo AND t.from_station=:fromStation AND  t.to_staion=:toStation  AND date_of_journey=:date ",nativeQuery=true)
    String getAvailable_T1AC(int trainNo, String fromStation, String toStation, LocalDate date);

	@Query(value="SELECT availtatkal2acseats FROM trip t  WHERE t.train_number=:trainNo AND t.from_station=:fromStation AND  t.to_staion=:toStation  AND date_of_journey=:date ",nativeQuery=true)
    String getAvailable_T2AC(int trainNo, String fromStation, String toStation, LocalDate date);
	
	@Query(value="SELECT availtatkal3acseats FROM trip t  WHERE t.train_number=:trainNo AND t.from_station=:fromStation AND  t.to_staion=:toStation  AND date_of_journey=:date ",nativeQuery=true)
    String getAvailable_T3AC(int trainNo, String fromStation, String toStation, LocalDate date);

	
	@Query(value="SELECT availableslseats FROM trip t  WHERE t.tripcode=:tripCode ",nativeQuery=true)
    String getAvailableSeats(String tripCode);
	
	@Query(value="SELECT avail_able_thired_ac_seats FROM trip t  WHERE t.tripcode=:tripCode ",nativeQuery=true)
	String getAvailable3ASeats(String tripCode);
	
	@Query(value="SELECT available_second_acseats FROM trip t  WHERE t.tripcode=:tripCode ",nativeQuery=true)
	String getAvailable2Aseats(String tripCode);
	
	@Query(value="SELECT available_first_acseats FROM trip t  WHERE t.tripcode=:tripCode ",nativeQuery=true)
	String getAvailable1ASeats(String tripCode);
	
	
}
