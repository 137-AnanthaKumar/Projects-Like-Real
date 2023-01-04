package com.example.irctc.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.irctc.commen.Helper;
import com.example.irctc.model.Route;
import com.example.irctc.model.Slots;
import com.example.irctc.model.Train;
import com.example.irctc.model.Trip;
import com.example.irctc.repo.RouteRepo;
import com.example.irctc.repo.SlotRepo;
import com.example.irctc.repo.TrainRepo;
import com.example.irctc.repo.TripRepo;

@Service
public class TripService {
	@Autowired
	private TrainRepo trainRepo;
	
	@Autowired 
	private SlotRepo slotsRepo;
	
	@Autowired 
	private Helper helper;

	
	
	
	@Autowired
	private TripRepo tripRepo;
	
	@Autowired
	private RouteRepo repoRoute;
	
	
	
	public Trip createNewTrip(Trip trip, String routdId){
		System.out.println(trip.getStartTime());
		int trainNo=trip.getTrainNo();
		Train trainDetail=trainRepo.getTrainDetails(trainNo);
		
		int totalCoach=trainDetail.getTotalCoach();
		int total_seats_perCoach=80;
		
		int total_SL_coach=trainDetail.getTotalSeeperClass();
		int total_3AC_Coach=trainDetail.getTotalThirdClassAc();
		int total_2AC_coach=trainDetail.getTotalSecondClassAc();
		int total_1Ac_Coach=trainDetail.getTotalFirstclassAcCouch();
		
		int total_SL_Seats=trainDetail.getTotalSleeperclassSeat();
		int total_2A_Seats=trainDetail.getTotalSecondClassAcSeats();
		int total_3A_Seats=trainDetail.getTotalThirdClassAcSeats();
		int total_1A_Seats=trainDetail.getTotalFirstclassAcCouchSeats();
		
		Trip newtrip=new Trip();
		//newtrip.getRoute().getStations();
		System.out.println(helper.getRandomNum(8));
		System.out.println(helper.getRandomNum(7));
		newtrip.setTripcode(helper.getRandomNum(7));
		newtrip.setFromStation(trip.getFromStation());
		newtrip.setToStaion(trip.getToStaion());
		
		newtrip.setStartTime(trip.getStartTime());
		newtrip.setEndTime(trip.getEndTime());
		newtrip.setDateOfJourney(trip.getDateOfJourney());
		newtrip.setEndOfJourney(trip.getEndOfJourney());
		
		
		newtrip.setSecondclassAcPrize(trip.getSecondclassAcPrize());
		newtrip.setFirstClassAcPrize(trip.getFirstClassAcPrize());
		newtrip.setThirdClassAcPrize(trip.getThirdClassAcPrize());
		newtrip.setSleeperPrize(trip.getSleeperPrize());
		
		newtrip.setTotalFirstAcseats(total_1A_Seats);
		newtrip.setTotalSecondAcseats(total_2A_Seats);
		newtrip.setTotalThirdAcseats(total_3A_Seats);
		newtrip.setTotalSLseats(total_SL_Seats);
		
		newtrip.setTatkal1Acseats(trip.getTatkal1Acseats());
		newtrip.setTatkalForSl(trip.getTatkalForSl());
		newtrip.setTatkalForThirdAc(trip.getTatkalForThirdAc());
		newtrip.setTatkalForSecondAc(trip.getTatkalForSecondAc());
		
		newtrip.setAvailTatkal1ACseats(String.valueOf(trip.getTatkal1Acseats()));
		newtrip.setAvailtatkal3ACseats(String.valueOf(trip.getTatkalForThirdAc()));
		newtrip.setAvailtatkal2ACseats(String.valueOf(trip.getTatkalForSecondAc()));
		newtrip.setAvailTatkalSLseats(String.valueOf(trip.getTatkalForSl()));
		System.out.println("hh"+trip.getTatkalForSl());
		
		
		newtrip.setAvailableFirstAcseats(String.valueOf(total_1A_Seats-trip.getTatkal1Acseats()));
		newtrip.setAvailableSecondAcseats(String.valueOf(total_2A_Seats-trip.getTatkalForSecondAc()));
		newtrip.setAvailAbleThiredAcSeats(String.valueOf(total_3A_Seats-trip.getTatkalForThirdAc()));
		newtrip.setAvailableSLSeats(String.valueOf(total_SL_Seats-trip.getTatkalForSl()));
	
        Route route=repoRoute.getOne(routdId);
        newtrip.setRoute(route);
        newtrip.setTrain(trainDetail);
       Trip savedTrip= tripRepo.save(newtrip);
		
		int slotsForCoach=0;
		
		int createdSlots=0;
		int createdSlotsFor_3AC=0;
		int createdSlotsFor_2AC=0;    //SL 3A 2A 1A    
		int createdSlotsFor_1AC=0;
		int createdSlotsFor_Sl=0;
		 String coach_3AC="B";
		 String coach_2AC="A";
		 String coach_1Ac="AA";
    	 String couch_SL="S";
   System.out.println(newtrip.getAvailableSecondAcseats());
	
     while(slotsForCoach<totalCoach) {

    	 //3AC 
    	 while(createdSlotsFor_3AC<total_3AC_Coach) {

    		 for(int i=0;i<total_seats_perCoach;i++) {
    			 Slots slotsObj=new Slots();

                 String coachName=coach_3AC+(createdSlotsFor_3AC+1);
        		 slotsObj.setCoachNo(coachName);
        		 slotsObj.setCoachposition(slotsForCoach+1);
        		 slotsObj.setCoachType("3A");
        		 slotsObj.setSeatNo(i+1);
        		 slotsObj.setTrip(newtrip);
        		slotsRepo.save(slotsObj);
        		createdSlots++;
    		 }
    		 slotsForCoach++;
    		 createdSlotsFor_3AC++;
    	 }
    	 
    	 
    	 while(createdSlotsFor_2AC<total_2AC_coach) {
    		 for(int i=0;i<total_seats_perCoach;i++) {
    			 Slots slotsObj=new Slots();
                 String coachName=coach_2AC+(createdSlotsFor_2AC+1);
        		 slotsObj.setCoachNo(coachName);
        		 slotsObj.setCoachposition(slotsForCoach+1);
        		 slotsObj.setCoachType("2A");
        		 slotsObj.setSeatNo(i+1);
        		 slotsObj.setTrip(newtrip);
         		slotsRepo.save(slotsObj);         	

            createdSlots++;
    		 }
    		 slotsForCoach++;
    		 createdSlotsFor_2AC++;
    	 }
    	 
    	 while(createdSlotsFor_1AC<total_1Ac_Coach) {
    		 for(int i=0;i<total_seats_perCoach;i++) {
    			 Slots slotsObj=new Slots();

                 String coachName=coach_1Ac+(createdSlotsFor_1AC+1);
        		 slotsObj.setCoachNo(coachName);
        		 slotsObj.setCoachposition(slotsForCoach+1);
        		 slotsObj.setCoachType("1A");
        		 slotsObj.setSeatNo(i+1);
        		 slotsObj.setTrip(newtrip);
         		slotsRepo.save(slotsObj);

          
        		 createdSlots++;
    		 }
    		 slotsForCoach++;

    		 createdSlotsFor_1AC++;
    	 }
    	 
    	 while(createdSlotsFor_Sl<total_SL_coach) {
    		 for(int i=0;i<total_seats_perCoach;i++) {
    			 Slots slotsObj=new Slots();
                   String coachName=couch_SL+(createdSlotsFor_Sl+1);
        		 slotsObj.setCoachNo(coachName);
        		 slotsObj.setCoachposition(slotsForCoach+1);
        		 slotsObj.setCoachType("SL");
        		  slotsObj.setSeatNo(i+1);
        		  slotsObj.setTrip(newtrip);
          		slotsRepo.save(slotsObj);
        		 createdSlots++;
    		 }
    		 slotsForCoach++;
    		 createdSlotsFor_Sl++;
    		 
    	 }
    	 
    
    	 
    	 
     }
    

    // trainRepo.save(trainDetail);
  
  return null;
		
	}
	
	
	
	public void delete(String code) {
		tripRepo.deleteById(code);
		
	}
	
	
	
}
