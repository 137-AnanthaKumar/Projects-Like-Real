package com.example.irctc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.irctc.model.RailwayStation;
@Repository
public interface RailwayStationRepo extends JpaRepository<RailwayStation, String> {

	Boolean existsByStationCode(String stationCode);

	
	@Query(value="SELECT * FROM irctc.railway_station where station_name LIKE :letter%",nativeQuery=true)
	List<RailwayStation> getStartWith(String letter);

	@Query(value="SELECT station_name FROM railway_station where station_code=:staTionCode",nativeQuery=true)
	String getStationName(String staTionCode);
	
	

	

}
