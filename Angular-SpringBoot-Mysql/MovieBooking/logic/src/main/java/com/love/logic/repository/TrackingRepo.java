package com.love.logic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.love.logic.models.Tracking;

@Repository
public interface TrackingRepo extends JpaRepository<Tracking, Integer> {
	//select *from Clients limit 0,10;

	@Query(value="SELECT * FROM tracking  ORDER BY date DESC, time DESC limit :rangeFrom,:rangeTo",nativeQuery=true)
	 List<Tracking> getDataFromTracking(Integer rangeFrom, Integer rangeTo);

	@Query(value="SELECT * FROM tracking WHERE whodid=:code ORDER BY date DESC, time DESC limit :rangeFrom,:rangeTo",nativeQuery=true)
	List<Tracking> getDataFromTracking(Integer rangeFrom, Integer rangeTo, Integer code);
	

}
