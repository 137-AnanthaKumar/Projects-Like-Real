package com.example.irctc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.irctc.dto.response.DistanceDto;
import com.example.irctc.dto.response.SampleResponse;
import com.example.irctc.model.DistanceBetweenStation;
@Repository
public interface DistanceBetweenStationRepo extends JpaRepository<DistanceBetweenStation, Integer> {


	@Query(value="SELECT * from distance_between_station WHERE (station2=:fromStation AND station1=:toStaion) OR (station1=:fromStation AND station2=:toStaion)",nativeQuery = true)
	DistanceBetweenStation existRecord(String fromStation, String toStaion);
	
	@Query(value="SELECT distance ,station1 from distance_between_station WHERE station2='MS' ",nativeQuery = true)
    DistanceDto getSomeData();
	
	

}
