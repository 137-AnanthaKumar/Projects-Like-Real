package com.example.irctc.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.irctc.model.TripScheduler;
import com.example.irctc.repo.ScheduledTripsRepo;

@Service
public class ScheduleService {
	
	@Autowired 
	private ScheduledTripsRepo repo;

	public String addingNewScedule(TripScheduler tripScheduleObj) {
		
		List<TripScheduler> schedul=repo.getActiveScheduleForThisTrain(tripScheduleObj.getTrainNo());
		String response;
		boolean alreadyPrecent = false;
		if(schedul.size()>0) {
		      System.out.println("hello");

			for(TripScheduler obj:schedul) {
			    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
				Date date1=obj.getScheduleStartingDate();
				Date date2=obj.getScheduleEndingDate();
				Date newDate=tripScheduleObj.getScheduleStartingDate();
				 String currDt = sdf.format(newDate);

				 if ((newDate.after(date1) && (newDate.before(date2)))
					        || (currDt.equals(sdf.format(date1)) || currDt.equals(sdf
					            .format(date2)))) {
					   alreadyPrecent=true;
					      System.out.println("Date is between ");
					    } else {
					      System.out.println("Date is not between .");
					      
					    }
		}
			
			}
		
		if(alreadyPrecent) {
			System.out.println("YOU CAn NOT Add");
			 response="CANNOTADD";

		}
		else {
			System.out.println("You CAn ADD");
			 response=addNewSchedule(tripScheduleObj);
			 
    }
		return response;
		

	}
	
	
	public String addNewSchedule(TripScheduler tripScheduleObj) {
		TripScheduler obj=repo.save(tripScheduleObj);
		if(obj!=null) {
			return "FINE";
		}
		
		return "NOTFINE";
		
	}

}
