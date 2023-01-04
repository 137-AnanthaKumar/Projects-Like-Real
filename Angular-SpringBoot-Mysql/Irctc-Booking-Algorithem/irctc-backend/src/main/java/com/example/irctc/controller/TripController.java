package com.example.irctc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.irctc.model.Trip;
import com.example.irctc.repo.TripRepo;
import com.example.irctc.services.TripService;

@RestController
@RequestMapping("/irctc/trip")
public class TripController {
	@Autowired
	private TripService tripService;
	
	@Autowired
	private TripRepo repoTrip;
	
	@PostMapping("/createTrip/{routdId}")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Trip> createTrip(@RequestBody Trip trip, @PathVariable String routdId) {
		
		Trip createdTrip=tripService.createNewTrip(trip,routdId);
		
		//tripService.delete("");
		
		return ResponseEntity.ok(createdTrip);
		
	}
	
	
//	@GetMapping("/gettrip/{tripCode}")
//    @PreAuthorize("hasRole('ADMIN')")
//	public void getTrip(@PathVariable String tripCode){
//		Trip get=repoTrip.getOne(tripCode);
//		System.out.println(get.getRoute().getDistance());
//	
//
//	}

	

}
