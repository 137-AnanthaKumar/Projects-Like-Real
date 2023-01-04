package com.example.irctc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.irctc.dto.response.Response;
import com.example.irctc.model.RailwayStation;
import com.example.irctc.repo.RailwayStationRepo;
import com.example.irctc.services.RailwayStationService;

@RestController
@RequestMapping("/station")
public class RailwayStationController {
	
	
	@Autowired
	private RailwayStationService stationService;
	
	@PostMapping("/addnewStattion")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> addNewStation(@RequestBody RailwayStation ststion) {
		
		RailwayStation obj=stationService.addNewTrain(ststion);
		Response res=new Response();
		res.setStatus("Creted");
		res.setStation(obj);
		return ResponseEntity.ok(res);
		}
	
	
	
	

}
