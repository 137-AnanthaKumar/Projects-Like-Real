package com.example.irctc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.irctc.exception.TrainNameAndNoAvailable;
import com.example.irctc.model.RailwayStation;
import com.example.irctc.repo.RailwayStationRepo;

@Service
public class RailwayStationService {
	
	@Autowired
	private RailwayStationRepo stationRepo;

	public RailwayStation addNewTrain(RailwayStation ststion) {
		
		Boolean stationCodAvail=stationRepo.existsByStationCode(ststion.getStationCode());
		if(stationCodAvail) {
			throw new TrainNameAndNoAvailable("TRAIN CODE ALREADY AVAILABLE");
		}
		if(!stationCodAvail) {
			RailwayStation station=stationRepo.save(ststion);
			return station;
		}
		return null;
	}

}
