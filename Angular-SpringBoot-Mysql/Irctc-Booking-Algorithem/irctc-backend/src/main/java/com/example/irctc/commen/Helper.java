package com.example.irctc.commen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.example.irctc.dto.response.TicketAvailResponse;
import com.example.irctc.model.Trip;

@Component
public class Helper {

	
	public String getRandomNum(int length_of_random) {
		
	
		  String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	      String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
	      String specialCharacters = "@#$";
	      String numbers = "1234567890";
	      String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
	      Random random = new Random();
	      char[] password = new char[length_of_random];

	      password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
	      password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
	      password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
	      password[3] = numbers.charAt(random.nextInt(numbers.length()));
	   
	      for(int i = 4; i< length_of_random ; i++) {
	         password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
	      }
	      
	      String pass=String.valueOf(password);
	    //  onj.setMovieID(pass);
		return pass;
	}
	
	
	public Long getNewPnr() {
		int min = 99999999;  
		int max = 60000001;  
	int b = (int)(Math.random()*(max-min+1)+min);  
	Date dss=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		String dat=sdf.format(dss);
	    String	str1 = dat.replaceAll("[^a-zA-Z0-9]", ""); 
	    String str2=str1.substring(2, 4);

	    String finala=b+str2;
	    Long pnr=Long.parseLong(finala);
	    System.out.println("PNR "+pnr);
	    return pnr;
	}


	public List<TicketAvailResponse> fetchAvailTicket(Trip train, HashSet<String> classTypes) {
	
		List<TicketAvailResponse> response=new ArrayList<TicketAvailResponse>();
		for(String val:classTypes) {
			TicketAvailResponse obj = null;
			switch (val) {
			case "SL":
				obj=new TicketAvailResponse(val,train.getAvailableSLSeats(),train.getDateOfJourney(),train.getSleeperPrize());
				break;
			case "3A":
				obj=new TicketAvailResponse(val,train.getAvailAbleThiredAcSeats(),train.getDateOfJourney(),train.getThirdClassAcPrize());
				break;	
			case "2A":
				obj=new TicketAvailResponse(val,train.getAvailableSecondAcseats(),train.getDateOfJourney(),train.getSecondclassAcPrize());
				break;
			case "1A":
				obj=new TicketAvailResponse(val,train.getAvailableFirstAcseats(),train.getDateOfJourney(),train.getFirstClassAcPrize());
				break;	
			
			}
			response.add(obj);
			
		}
		return response;
	}


	public TicketAvailResponse fetchAvailTicketForReview(Trip train, String coachType) {
		TicketAvailResponse obj = null;

		switch (coachType) {
		case "SL":
			obj=new TicketAvailResponse(coachType,train.getAvailableSLSeats(),train.getDateOfJourney(),train.getSleeperPrize());
			break;
		case "3A":
			obj=new TicketAvailResponse(coachType,train.getAvailAbleThiredAcSeats(),train.getDateOfJourney(),train.getThirdClassAcPrize());
			break;	
		case "2A":
			obj=new TicketAvailResponse(coachType,train.getAvailableSecondAcseats(),train.getDateOfJourney(),train.getSecondclassAcPrize());
			break;
		case "1A":
			obj=new TicketAvailResponse(coachType,train.getAvailableFirstAcseats(),train.getDateOfJourney(),train.getFirstClassAcPrize());
			break;	
		
		}
		return obj;
	}
	 
}
