package com.example.irctc.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.irctc.model.TripScheduler;
import com.example.irctc.repo.ScheduledTripsRepo;

@Component
public class Scheduler {
	
	@Autowired
	private ScheduledTripsRepo scanRepo;

	int a=1;
	@Scheduled(fixedRate = 20000)
	public void scheduleTaskWithFixedRate() {
		
	  List<TripScheduler> listForUpcomingSchedule=scanRepo.getActiveSchedules();
	  System.out.println(listForUpcomingSchedule.size());
	   System.out.println("SCHUDEL "+a);
	   a=a+1;
	   
	   
	}

}
