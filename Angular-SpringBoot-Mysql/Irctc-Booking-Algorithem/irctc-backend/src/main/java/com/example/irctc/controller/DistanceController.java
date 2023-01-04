package com.example.irctc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.irctc.dto.response.DistanceDto;
import com.example.irctc.dto.response.SampleResponse;
import com.example.irctc.model.DistanceBetweenStation;
import com.example.irctc.repo.DistanceBetweenStationRepo;

@RestController
@RequestMapping("/distance")
public class DistanceController {
	@Autowired
	private DistanceBetweenStationRepo repo;
	
	@GetMapping("/getdata")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getSomeData(){
		

		DistanceDto ojh= repo.getSomeData();
		
		
		return ResponseEntity.ok(ojh);
		
	}

}
