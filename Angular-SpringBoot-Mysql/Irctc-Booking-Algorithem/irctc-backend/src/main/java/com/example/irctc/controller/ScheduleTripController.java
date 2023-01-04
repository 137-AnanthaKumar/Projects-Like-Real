package com.example.irctc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.irctc.model.TripScheduler;
import com.example.irctc.services.ScheduleService;

@RestController
@RequestMapping("/irctc/tripSchedule")
public class ScheduleTripController {
	
	@Autowired
	private ScheduleService scheduleService;
	
	@PostMapping
    @PreAuthorize("hasRole('ADMIN')")
	public String addNewSchedule(@RequestBody TripScheduler tripScheduleObj){
		
		String res=scheduleService.addingNewScedule(tripScheduleObj);
		
      return res;
		
	}

}
